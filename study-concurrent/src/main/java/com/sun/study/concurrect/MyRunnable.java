package com.sun.study.concurrect;

public class MyRunnable implements Runnable {
    
    @Override
    public void run() {
        System.out.println("线程run：" + Thread.currentThread().getName());
    }

}
