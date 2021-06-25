package com.sun.study.concurrect;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class Main {
    
    /**
     * 自定义线程名称,方便的出错的时候溯源
     */
    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("自定义线程池前缀：").build();

    private static final ExecutorService pool = new ThreadPoolExecutor(
            4,
            40,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy()
    ) {
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println("后置处理：" + t);
        }
    };
    
    public static void main(String[] args) throws Exception {
        System.out.println("继承Thread：");
        MyThread thread = new MyThread();
        thread.start();

        System.out.println("实现Runnable：");
        // MyRunnable thread2 = new MyRunnable();
        MyRunnable thread2 = new MyRunnable() {
            @Override
            public void run() {
                super.run();
                System.out.println("线程run：" + Thread.currentThread().getName() + "\t\t\t我重写了");
            }
        };
        new Thread(thread2).start();

        System.out.println("实现Callable：");
        // 实现Callable方式：new Thread(FutureTask)
        /*MyCallable thread3 = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(thread3);
        new Thread(futureTask).start();
        String call = futureTask.get();
        System.out.println("接收回调-----" + call);*/
        
        // Executors.newFixedThreadPool()，使用ThreadPoolExecutor创建线程池
        MyCallable callable = new MyCallable();
        Future<String> future = pool.submit(callable);
        System.out.println("接收回调-----" + future.get());
        
        pool.shutdown();
    }
    
}
