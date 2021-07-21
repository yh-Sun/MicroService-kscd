package com.sun.study;

import lombok.Data;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Optional<A> a = Optional.of(new A());
        String b1 = Optional.of(a.get().getB().getB1()).orElseThrow(null);
        System.out.println(b1);
        ReentrantLock roke = new ReentrantLock();
    }

    @Data
    static class A {
        private String a1;
        private String a2;
        private String a3;
        private String a4;
        private B b;
    }

    @Data
    class B {
        private String b1;
    }

}
