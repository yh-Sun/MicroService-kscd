package com.sun.study.reentrant_lock;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 场景：一个简单的可重入锁，用来操作一个全部变量（最好写个不加锁的，看看有没有问题）
 *
 * @author  Sun
 * @date    2021/7/5 11:26
 * @since   1.0
 */
public class ReentrantLockDemo {

    private static final Random random = new Random();

    /** 有锁的count */
    private int count;

    /** 没有锁的count */
    private int num;


    public static void main(String[] args) throws InterruptedException {
        // 创建计数器，设定所有子线程执行完毕后，打印count
        CountDownLatch latch = new CountDownLatch(1000);

        // 创建可重入锁
        ReentrantLock lock = new ReentrantLock();

        ReentrantLockDemo main = new ReentrantLockDemo();
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(Thread.currentThread().getName());
                    main.starter(lock);
                    latch.countDown();
                }
            }, "线程：" + (i+1)).start();
        }

        latch.await();
        System.out.println(main.count);

        System.out.println("-----------------num--------------------");
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(random.nextInt(1000));
                    main.num++;
                    System.out.println("无锁" + Thread.currentThread().getName());
                }
            }, "线程：" + (i+1)).start();
        }

        // 不创建计数器了，直接休息10s，上面50个线程肯定能执行完成
        Thread.sleep(5000);
        System.out.println("num = " + main.num);

    }


    /**
     * 使用ReentrantLock
     */
    public void starter(ReentrantLock lock) {
        try {
            lock.lock();
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
