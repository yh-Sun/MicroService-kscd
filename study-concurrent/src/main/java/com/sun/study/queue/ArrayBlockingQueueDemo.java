package com.sun.study.queue;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BlockingQueueDemo是使用LinkedBlockingQueue实现队列的demo
 * 场景：手写一个ArrayBlockingQueue
 *
 * @author  Sun
 * @date    2021/7/5 17:12
 * @since   1.0
 */
public class ArrayBlockingQueueDemo {

    /** 可重入锁 */
    private final ReentrantLock lock;

    /** 监视器-保证队列不为空：1.生产者：队列塞入值时，释放；2.消费者：队列空时，等待 */
    private final Condition notEmpty;

    /** 监视器-保证队列不满：1.生产者：队列满时，等待；2.消费者：队列拿出值时，释放 */
    private final Condition notFull;

    private final Object[] items;

    /** 塞入指针（生产者） */
    private int putIndex;

    /** 拿出指针（消费者） */
    private int takeIndex;

    /** 队列中消息总数 */
    private int count;

    /**
     * 初始化队列器
     */
    public ArrayBlockingQueueDemo(int capacity) {
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
        this.items = new Object[capacity];
    }


    /**
     * 塞入：
     *  1.获取锁
     *  2.满了等待
     *      notFull等待
     *  3.不满塞
     *      putIndex指针移动
     *      count++
     *      notEmpty释放
     *  4.塞完 释放锁
     *
     */
    public void put(Object item) {
        try {
            lock.lock();
            if(count == items.length) {
                // System.out.println("zzzz" + Thread.currentThread().getName() + "队列满了，等待消费后塞入");
                notFull.await();
            }
            // 塞值
            if(putIndex == items.length - 1) {
                putIndex = 0;
            } else {
                putIndex ++;
            }
            items[putIndex] = item;
            count++;
            // System.out.println("zzzz" + Thread.currentThread().getName() + "已塞入，请继续消费");
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    /**
     * 拿出
     */
    public Object take() {
        Object item = null;
        try {
            lock.lock();
            if(count == 0) {
                // System.out.println("zzzz" + Thread.currentThread().getName() + "队列空了，请生产");
                notEmpty.await();
            }
            // 取值
            if(takeIndex == items.length - 1) {
                takeIndex = 0;
            } else {
                takeIndex++;
            }
            item = items[takeIndex];
            count--;
            // System.out.println("zzzz" + Thread.currentThread().getName() + "已消费，请继续生产");
            notFull.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return item;
    }

}


/**
 * 生产者
 */
class MyProvider implements Runnable{

    private final int num;

    private final ArrayBlockingQueueDemo queue;

    private final Random random = new Random();

    public MyProvider(int num, ArrayBlockingQueueDemo queue) {
        this.num = num;
        this.queue = queue;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(random.nextInt(3000));
        queue.put("生产-" + num);
        System.out.println("生产-" + num);
    }
}


/**
 * 消费者
 */
class MyCustomer implements Runnable{

    private final int num;

    private final ArrayBlockingQueueDemo queue;

    private final Random random = new Random();

    public MyCustomer(int num, ArrayBlockingQueueDemo queue) {
        this.num = num;
        this.queue = queue;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(random.nextInt(3000));
        Object take = queue.take();
        System.out.println("消费-" + num + "====" + take.toString());
    }
}


/**
 * 启动器
 *
 * @author  Sun
 * @date    2021/7/5 18:20
 * @since   1.0
 */
class Starter {

    public static void main(String[] args) {
        new Starter().starter();
    }

    /**
     * 启动器
     */
    public void starter() {
        // 创建10个数组队列
        ArrayBlockingQueueDemo queue = new ArrayBlockingQueueDemo(10);
        for (int i = 1; i <= 100; i++) {
            new Thread(new MyProvider(i, queue), "线程" + i).start();
            new Thread(new MyCustomer(i, queue), "线程" + i).start();
        }

    }
}
