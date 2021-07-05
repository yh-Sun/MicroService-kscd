package com.sun.study.test;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;


/**
 * 起因：https://blog.csdn.net/javabird945/article/details/80563344，对此博客写的内容有些异议，特此尝试
 * Thread - A===0
 * Thread - A===1
 * Thread - A===2
 * Thread - A===3
 * Thread - A===4
 * Thread - A===5
 * Thread - A===6
 * Thread - A===7
 * Thread - A===8
 * Thread - A===9
 * Thread - B===0
 * Thread - B===1
 * Thread - B===2
 * Thread - B===3
 * Thread - B===4
 * Thread - B===5
 * Thread - B===6
 * Thread - B===7
 * Thread - B===8
 * Thread - B===9
 * 总结：没有看明白博主的用意，我还在疑惑i明明是局部变量，又不是公共变量，怎么被两个线程同时操作。蠢了，人家只是说A锁住了，只执行A，A释放锁开始执行B
 *
 * @author  Sun
 * @date    2021/7/5 16:21
 * @since   1.0
 */
public class ReentrantLockConditionTest {

    public static void main(String[] args) {
        ReentrantLockConditionTest starter = new ReentrantLockConditionTest();
        starter.starter();
    }

    /**
     * 无锁的测试：两个线程同时操作一个变量
     */
    public void starter() {
        // 创建可重入锁
        ReentrantLock lock = new ReentrantLock();

        // AB两个线程对象分别获取lock锁，用完释放
        new Thread(new MyRunnable(lock), "A").start();
        new Thread(new MyRunnable(lock), "B").start();
    }


    /**
     * 线程执行方法时，先获取lock锁，执行完毕后释放
     */
    static class MyRunnable implements Runnable {

        private final ReentrantLock lock;

        MyRunnable(ReentrantLock lock) {
            this.lock = lock;
        }

        @SneakyThrows
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++){
                    System.out.println("Thread - " + Thread.currentThread().getName() + "===" + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }


}
