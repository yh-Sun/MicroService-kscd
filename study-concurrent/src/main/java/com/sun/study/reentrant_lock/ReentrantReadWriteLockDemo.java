package com.sun.study.reentrant_lock;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock使用读写锁demo
 * 场景：简单点吧，一个线程一直写，两个线程一直读
 *  附加1：再来个锁降级
 *
 * @author  Sun
 * @date    2021/7/5 15:47
 * @since   1.0
 */
public class ReentrantReadWriteLockDemo {

    private int i = 0;

    public static void main(String[] args) {
        new ReentrantReadWriteLockDemo().starter();
    }

    /**
     * 启动器
     */
    @SneakyThrows
    public void starter() {
        // 创建读写锁
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        // 获取读写锁
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        for (int j = 0; j < 20; j++) {
            new MyReader("线程1", readLock).start();
            new MyReader("线程2", readLock).start();
            new MyWriter("线程3", writeLock).start();
            new MyWriter("线程4", writeLock).start();
        }


        // 锁降级：有一个功能，要求写完后 还得跟领导汇报一下i（sout）


    }


    class MyReader extends Thread {

        private ReentrantReadWriteLock.ReadLock readLock;

        private Random random = new Random();

        public MyReader(String name, ReentrantReadWriteLock.ReadLock readLock) {
            super(name);
            this.readLock = readLock;
        }

        @Override
        public void run() {
            try {
                readLock.lock();
                System.out.println("读-" + Thread.currentThread().getName() + "开始：" + i);
//                Thread.sleep(random.nextInt(3000));
                System.out.println("读-" + Thread.currentThread().getName() + "结束：" + i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
    }


    class MyWriter extends Thread {

        private ReentrantReadWriteLock.WriteLock writeLock;

        private Random random = new Random();

        public MyWriter(String name, ReentrantReadWriteLock.WriteLock writeLock) {
            super(name);
            this.writeLock = writeLock;
        }

        @Override
        public void run() {
            try {
                writeLock.lock();
                System.out.println("写-" + Thread.currentThread().getName() + "开始：" + i++);
//                Thread.sleep(random.nextInt(1000));
                System.out.println("写-" + Thread.currentThread().getName() + "结束：" + i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
    }

}






