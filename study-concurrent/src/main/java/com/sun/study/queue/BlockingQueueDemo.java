package com.sun.study.queue;

import java.util.concurrent.*;

/**
 * 本例采用LinkedBlockingQueue测试阻塞队列
 * 
 * @author  Sun
 * @date    2021/6/29 11:50
 * @since   1.0
 */
public class BlockingQueueDemo {

    private static final ExecutorService pool = new ThreadPoolExecutor(
            4,
            40,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024)
    );
    
    
    public static void main(String[] args) throws InterruptedException {
        // 创建队列
        MyQueue queue = new MyQueue();
        // 创建线程池执行生产者、消费者
        for (int i = 0; i < 20; i++) {
            pool.submit(new MyProvider(queue));
            pool.submit(new MyCustomer(queue));
        }

        // 我调皮，又多消费了一次，看来得等10s才能消费到啊
        pool.submit(new MyCustomer(queue));
        Thread.sleep(10000);
        pool.submit(new MyProvider(queue));
        pool.shutdown();
    }
    
    
    /**
     * 队列，用于消费和生产
     */
    static class MyQueue {
        
        private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        
        public void put(String msg) throws InterruptedException {
            queue.put(msg);
        }

        public String take() throws InterruptedException {
             return queue.take();
        }
        
    }
    
    
    /**
     * 生产者
     */
    static class MyProvider implements Runnable {
        
        private final MyQueue queue;

        public MyProvider(MyQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String msg = "生产-" + Thread.currentThread().getName();
            System.out.println(msg);
            
            try {
                queue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }


    /**
     * 消费者
     */
    static class MyCustomer implements Runnable {

        private final MyQueue queue;

        public MyCustomer(MyQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                String msg = queue.take();
                System.out.println("消费-" + Thread.currentThread().getName() + "；信息体：" + msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

    }
    
}
