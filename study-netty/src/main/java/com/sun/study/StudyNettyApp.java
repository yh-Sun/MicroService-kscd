package com.sun.study;

import com.sun.study.chat_springboot.netty.NettyWebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyNettyApp implements CommandLineRunner {

    @Autowired
    NettyWebSocketServer nettyWebSocketServer;

    public static void main(String[] args) {
        SpringApplication.run(StudyNettyApp.class, args);
    }

    @Override
    public void run(String... args) {
        new Thread(nettyWebSocketServer).start();
    }

}
