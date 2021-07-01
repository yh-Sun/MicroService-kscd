package com.sun.study.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 本例采用ConcurrentLinkedQueue测试非阻塞队列
 * 上一个BlockLinkedQueue采用的是：多个线程生成、多个线程消费（每个线程只生产/消费一次）
 * 本例采用：一个线程生产20个消息，创建20个线程来消费他们。
 *  附加：
 * 
 * @author  Sun
 * @date    2021/7/1 9:16
 * @since   1.0
 */
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueueDemo starter = new ConcurrentLinkedQueueDemo();
        starter.starter();
    }
    
    private final int count = 20;

    private final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    private static final ExecutorService pool = new ThreadPoolExecutor(
            4,
            40,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024)
    );
    
    private static final List<Future<String>> futureList = new ArrayList<>();
    
    
    public void starter() throws InterruptedException {
        // 线程完成计数器
        CountDownLatch latch = new CountDownLatch(count);
        
        // 生产消息
        new Thread(new MyProvider("生产者", latch)).start();

        // 线程池消费消息
        for (int i = 0; i < count; i++) {
            Future<String> submit = pool.submit(new MyCustomer("消费者" + (i + 1), latch));
            futureList.add(submit);
        }
        
        // 等待所有线程消费完成
        latch.await();
        System.out.println("所有线程都已经执行完毕");
        List<String> msgList = futureList.stream().map(stringFuture -> {
            try {
                return stringFuture.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        System.out.println(msgList);
        
        // 关闭线程池
        pool.shutdown();
    }
    
    /**
     * 生产者
     */
    class MyProvider implements Runnable {

        private String name;
        
        private CountDownLatch latch;

        public MyProvider(String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                String msg = "线程：" + name + "生产消息" + (i + 1);
                queue.offer(msg);
                System.out.println(msg);
            }
        }
        
    }

    /**
     * 消费者
     */
    class MyCustomer implements Callable<String> {

        private String name;

        private CountDownLatch latch;

        public MyCustomer(String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }

        @Override
        public String call() {
            String msg = queue.poll();
            System.out.println(name + "--------消费------------" + msg);
            latch.countDown();
            return msg;
        }

    }
    
}
