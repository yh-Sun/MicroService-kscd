package com.sun.study.atomic;

import cn.hutool.core.lang.Assert;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 场景：多个线程操作一个数据，由于普通的AtomicInter存在ABA问题，特此Demo解决
 *
 * @author  Sun
 * @date    2021/7/14 15:58
 * @since   1.0
 */
public class AtomicStampedReferenceDemo {

    private static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(0, 0);

    public static void main(String[] args) {
        AtomicStampedReferenceDemo starter = new AtomicStampedReferenceDemo();
        starter.starter();
    }

    public void starter() {
        // 1000个线程操作一个变量，这个肯定是基本功能
        /*for (int i = 0; i < 1000; i++) {
            new MyThread("线程" + i).start();
        }*/

        // 解决ABA问题
        int stamp1 = reference.getStamp();
        System.out.println(reference.compareAndSet(0, 1, stamp1, stamp1 + 1));
        int stamp2 = reference.getStamp();
        System.out.println(reference.compareAndSet(1, 0, stamp2, stamp2 + 1));
        int stamp3 = stamp1;
        boolean b3 = reference.compareAndSet(0, 2, stamp3, stamp3 + 1);
        Assert.isTrue(b3);

    }


    public class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int stamp = reference.getStamp();
            Integer old = reference.getReference();
            boolean b = reference.compareAndSet(old, old + 1, stamp, stamp + 1);
            System.out.println(getName() + b + "-" + reference.getReference() + "-");
        }
    }

}
