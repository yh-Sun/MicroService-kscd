package com.sun.study.semaphore;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


/**
 * 本例不再使用ReentrantLock锁，采用计数阈值信号量Semaphore
 *
 * @author  Sun
 * @date    2021/7/9 16:01
 * @since   1.0
 */
public class Main {

    private static final Random random = new Random();

    /** 有锁的count */
    private int count;

    /** 没有锁的count */
    private int num;


    public static void main(String[] args) throws InterruptedException {
        // 创建计数器，设定所有子线程执行完毕后，打印count
        CountDownLatch latch = new CountDownLatch(10000);

        // 创建可重入锁
        Semaphore semaphore = new Semaphore(1);

        Main main = new Main();
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(random.nextInt(100));
                    System.out.println(Thread.currentThread().getName());
                    main.starter(semaphore);
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
                    // System.out.println("无锁" + Thread.currentThread().getName());
                }
            }, "线程：" + (i+1)).start();
        }

        // 不创建计数器了，直接休息10s，上面50个线程肯定能执行完成
        Thread.sleep(5000);
        System.out.println("num = " + main.num);

    }


    /**
     * 使用Semaphore
     */
    public void starter(Semaphore semaphore) {
        try {
            // 申请许可
            semaphore.acquire();
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放许可
            semaphore.release();
        }

    }

}
