package com.sun.study.count_down_latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 * 场景：还是面试吧，这次 我们分为三个阶段（到达面试地点、笔试、面试）
 * 
 * @author  Sun
 * @date    2021/7/1 13:40
 * @since   1.0
 */
public class CyclicBarrierDemo {
    
    // 线程数
    private final int threadNum = 10;
    
    // 定义全局线程计数器
    private final CountDownLatch latch = new CountDownLatch(threadNum);
    

    public static void main(String[] args) throws InterruptedException {
        new CyclicBarrierDemo().starter();
    }
    
    public void starter() throws InterruptedException {
        System.out.println("通知N位面试者面试");
        
        // 创建循环栅栏计数器
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "---------完成最后任务");
            }
        });
        
        
        for (int i = 1; i <= threadNum; i++) {
            new Thread(new MyRunnable(barrier), "面试者" + i).start();
        }

        
        System.out.println("等待所有面试者完成面试");
        latch.await();
        System.out.println("面试完成，记录分数，分发offer");
    }
    
    
    /**
     * 面试者
     */
    class MyRunnable implements Runnable {
        
        private final CyclicBarrier barrier;

        private final Random random = new Random();

        public MyRunnable(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "出发");
                Thread.sleep(random.nextInt(2000));
                System.out.println(Thread.currentThread().getName() + "到达面试地点");
                // 等待所有面试者到达后，开始笔试
                barrier.await();
                
                System.out.println(Thread.currentThread().getName() + "笔试开始");
                Thread.sleep(random.nextInt(2000));
                System.out.println(Thread.currentThread().getName() + "笔试答题结束，交卷");
                // 等待所有面试者交卷后，开始面试
                barrier.await();
    
                System.out.println(Thread.currentThread().getName() + "开始面试");
                Thread.sleep(random.nextInt(3000));
                System.out.println(Thread.currentThread().getName() + "面试完成");
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
