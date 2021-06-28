package com.sun.study.interrupt;

/**
 * interrupt方法结束线程
 * 
 * @author  Sun
 * @date    2021/6/25 14:36
 * @since   1.0
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        InterruptThread thread = new InterruptThread();
        thread.start();
        
        Thread.sleep(10000);
        System.out.println("Main要求thread子线程中断");
        thread.interrupt();
        System.out.println("thread子线程已经中断");
    }
    
}
