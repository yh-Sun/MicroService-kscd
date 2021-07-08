package com.sun.study.reentrant_lock;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁、锁降级：实现数据缓存
 *
 * 场景：有一个HR人力资源管理系统，此系统会周期性通知你是否有新员工入职（周期性任务调度），
 * 如果有新员工入职，在你下次读的时候就重新去拉取一次（此处不要杠说为什么不通知你的时候就拉取，你见过老板干活么）
 *
 * @author  Sun
 * @date    2021/7/8 13:51
 * @since   1.0
 */
public class ReentrantReadWriteLockCacheDemo {

    private final List<String> data = new ArrayList<>();;
    // 缓存是否为最新数据
    private volatile boolean cacheValid;


    public static void main(String[] args) {
        ReentrantReadWriteLockCacheDemo demo = new ReentrantReadWriteLockCacheDemo();

        // 10个线程一直读数据
        demo.starter();

        // 周期性任务调度，通知是否有新员工入职
        demo.scheduled();
    }

    public void starter() {
        // 获取读写锁
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        new Thread(() -> {
            for (int i = 1; i < 500; i++) {
                new MyReader("线程" + i++, lock.writeLock(), lock.readLock()).start();
                if(i % 2 == 0) {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    class MyReader extends Thread {

        ReentrantReadWriteLock.WriteLock writeLock;

        ReentrantReadWriteLock.ReadLock readLock;

        public MyReader(String name, ReentrantReadWriteLock.WriteLock writeLock, ReentrantReadWriteLock.ReadLock readLock) {
            super(name);
            this.writeLock = writeLock;
            this.readLock = readLock;
        }

        @Override
        public void run() {
            System.out.println(getName() + "开始-获取读锁");
            readLock.lock();
            System.out.println(getName() + "成功-获取读锁");
            // 缓存失败，需要重新获取数据写入缓存
            System.out.println(getName() + cacheValid);
            if(!cacheValid) {
                System.out.println(getName() + "开始-释放读锁");
                readLock.unlock();
                System.out.println(getName() + "成功-释放读锁");
                System.out.println(getName() + "开始-获取写锁");
                writeLock.lock();
                System.out.println(getName() + "成功-获取写锁");
                try {
                    System.out.println(getName() + cacheValid);
                    if(!cacheValid) {
                        System.out.println(getName() + "开始-获取数据");
                        data.add("0");
                        cacheValid = true;
                        System.out.println(getName() + "cacheValid-更新");
                    }
                    System.out.println(getName() + "开始-获取读锁");
                    readLock.lock();
                    System.out.println(getName() + "成功-获取读锁");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(getName() + "开始-释放写锁");
                    writeLock.unlock();
                    System.out.println(getName() + "成功-释放写锁");
                }
            }

            try {
                System.out.println(getName() + "输出HR数据：" + data.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(getName() + "开始-释放读锁");
                readLock.unlock();
                System.out.println(getName() + "成功-释放读锁");
            }
        }
    }


    public void scheduled() {
        // 单个后台线程执行周期任务
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // 每隔10s执行一次
        executor.scheduleAtFixedRate(new Runnable() {
            private final Random random = new Random();
            @Override
            public void run() {
                if(random.nextBoolean()) {
                    System.out.println("-------------------数据有更新");
                    cacheValid = false;
                } else {
                    System.out.println("-------------------数据无更新，无需重新获取");
                }
            }
        }, 5, 60, TimeUnit.MILLISECONDS);
    }

}
