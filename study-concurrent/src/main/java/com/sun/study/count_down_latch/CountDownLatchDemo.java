package com.sun.study.count_down_latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch计数器，由于com/sun/study/queue/ConcurrentLinkedQueueDemo.java已经使用，本例稍稍简单一些
 * 场景：20个人同时接到去面试的信息，有的人拖沓耽搁一会、有的人立马出发，你是一个人事，所有人都到场后 开启面试
 * 
 * @author  Sun
 * @date    2021/7/1 11:17
 * @since   1.0
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo starter = new CountDownLatchDemo();
        starter.starter();
    }
    
    public void starter() throws InterruptedException {
        // 创建计数器
        CountDownLatch latch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(new MyRunnable("面试者" + (i + 1), latch)).start();
        }

        System.out.println("等待所有面试者到达");
        latch.await();
        System.out.println("所有面试者都已经到达");
    }
    
    
    static class MyRunnable implements Runnable {

        private final String name;
        
        private final CountDownLatch latch;

        private final Random random = new Random();
        
        public MyRunnable(String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }

        @Override
        public void run() {
            // 准备出发
            try {
                Thread.sleep(random.nextInt(4000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "到了，还有" + latch.getCount() + "位");
            latch.countDown();
        }
        
    }
    
    
}
