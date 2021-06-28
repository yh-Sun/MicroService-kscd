package com.sun.study.interrupt;

public class InterruptThread extends Thread{

    @Override
    public void run() {
        // 1.非阻塞过程中，通过判断isInterrupted判断中断标志来退出
        while (!isInterrupted()) {
            try {
                // 2.阻塞过程中，捕获中断异常来退出
                System.out.println("线程开始休息2s");
                Thread.sleep(2000);
                System.out.println("休息结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
                // 2.1 捕获异常后，通过break;来跳出循环
                break;
            }
        }
    }
}
