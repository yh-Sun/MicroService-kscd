package com.sun.study.concurrect;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    
    @Override
    public String call() throws Exception {
        String result = "线程run：" + Thread.currentThread().getName();
        System.out.println(result);
        Thread.sleep(5000);
        return result;
    }
    
}
