package com.sun.study.concurrect;

public class MyThread extends Thread {
    
    @Override
    public void run() {
        System.out.println("线程run：" + getName());
    }
    
}
