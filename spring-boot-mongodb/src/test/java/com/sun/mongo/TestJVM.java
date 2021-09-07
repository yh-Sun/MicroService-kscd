package com.sun.mongo;


import org.junit.Test;

public class TestJVM {

    /**
     * CPU过高，jstack -l 进程id
     * "main" #1 prio=5 os_prio=0 cpu=12156.25ms elapsed=12.96s tid=0x00000299b9798800 nid=0x5408 runnable  [0x000000b354cff000]
     *    java.lang.Thread.State: RUNNABLE
     *         at com.sun.mongo.TestJVM.main(TestJVM.java:5)
     *
     *    Locked ownable synchronizers:
     *         - None
     */
    @Test
    public void cpuTop() {
        System.out.println("开始");
        while (true) {

        }
    }

}
