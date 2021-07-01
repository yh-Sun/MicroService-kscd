package com.sun.study.count_down_latch;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * 解决控制多个线程分阶段共同完成任务的情景问题
 * 场景：还是面试吧，这次 我们分为三个阶段（到达面试地点、笔试、面试），现在要增加一个条件：不同的阶段结束后，都给面试者不同的资料
 *  增加场景1.面试只能是90分以上的人参加
 *  增加场景2.笔试结束后，经理外甥女来直接面试
 *
 * @author  Sun
 * @date    2021/7/1 14:18
 * @since   1.0
 */
public class PhaserDemo {
    
    public static void main(String[] args) {
        new PhaserDemo().starter();
    }
    
    public void starter() {
        Phaser phaser = new Phaser(5) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("phase : " + phase + "----registeredParties" + registeredParties);
                return registeredParties == 0;
            }
        };
        System.out.println("开启公司大门");
        for (int i = 1; i <= 5; i++) {
            new Thread(new MyRunnable(phaser), "面试者" + i).start();
        }

        while (true) {
            if(2 == phaser.getPhase()) {
                // 加三
                System.out.println("经理外甥女要参加面试");
                phaser.register();
                new Thread(new MyRunnable1(phaser), "经理外甥女").start();
                return;
            }
        }
        
    }
    
    
    class MyRunnable implements Runnable {
        
        private Phaser phaser;
        
        private Random random = new Random();

        public MyRunnable(Phaser phaser) {
            this.phaser = phaser;
        }
        

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "出发");
            Thread.sleep(random.nextInt(2000));
            System.out.println(Thread.currentThread().getName() + "到达面试地点");
            // 等待所有面试者到达后，开始笔试
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + "笔试开始");
            Thread.sleep(random.nextInt(2000));
            System.out.println(Thread.currentThread().getName() + "笔试答题结束，交卷");
            // 等待所有面试者交卷后，开始面试
            phaser.arriveAndAwaitAdvance();
            
            // 随机数：模拟是否有90分
            if(random.nextBoolean()) {
                // // 少于90分的 pass掉
                phaser.arriveAndDeregister();
            } else {
                // 超过90分的才有面试的权利
                System.out.println(Thread.currentThread().getName() + "开始面试");
                Thread.sleep(random.nextInt(3000));
                System.out.println(Thread.currentThread().getName() + "面试完成");
                phaser.arriveAndAwaitAdvance();
            }
        }
    }


    class MyRunnable1 implements Runnable {

        private Phaser phaser;

        public MyRunnable1(Phaser phaser) {
            this.phaser = phaser;
        }

        @SneakyThrows
        @Override
        public void run() {
            // 经理外甥女
            System.out.println(Thread.currentThread().getName() + "开始面试");
            System.out.println(Thread.currentThread().getName() + "面试完成");
            phaser.arriveAndAwaitAdvance();
        }
    }
    
}
