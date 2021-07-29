package com.sun.study.synchronized_example;

public class SynchronizedTest {

    public synchronized static void syncStatic() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(200000000);
    }

    public synchronized void sync() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(200000000);
    }

}
