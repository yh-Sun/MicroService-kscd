package com.sun.study.synchronized_example;

import lombok.SneakyThrows;

/**
 * Synchronized锁 静态方法、实例方法
 *
 * @author  Sun
 * @date    2021/7/19 16:45
 * @since   1.0
 */
public class Main {

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                SynchronizedTest.syncStatic();
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                test.sync();
            }
        }).start();


    }

}
