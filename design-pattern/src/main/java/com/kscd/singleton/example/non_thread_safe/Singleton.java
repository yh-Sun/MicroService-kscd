package com.kscd.singleton.example.non_thread_safe;


/**
 * 单线程：单例
 * 
 * @author  Sun
 * @date    2021/6/22 11:41
 * @since   1.0
 */
public final class Singleton {
    private static Singleton instance;
    public String value;

    private Singleton(String value) {
        // EN: The following code emulates slow initialization.
        //
        // RU: Этот код эмулирует медленную инициализацию.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);
        }
        return instance;
    }
}
