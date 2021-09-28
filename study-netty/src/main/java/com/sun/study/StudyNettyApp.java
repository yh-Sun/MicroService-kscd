package com.sun.study;

import cn.hutool.core.lang.Console;
import com.corundumstudio.socketio.SocketIOServer;
import com.sun.study.chat_springboot.netty.NettyWebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyNettyApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StudyNettyApp.class, args);
    }

    @Autowired
    private SocketIOServer socketIOServer;

    @Override
    public void run(String... strings) {
        socketIOServer.start();
        Console.log("socket.io启动成功！");
    }

}
