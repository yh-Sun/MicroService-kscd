package com.sun.study;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        final String s = "1";
        Optional<String> byPolicyNoOpt = Optional.of("1");
        byPolicyNoOpt.ifPresent(policyMain -> {
            System.out.println("111");
            // int i = 1 / 0;
        });
    }

}
