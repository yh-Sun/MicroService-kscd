package com.sun.study.netty_socketio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final MessageEventHandler messageEventHandler;

    public TestController(MessageEventHandler messageEventHandler) {
        this.messageEventHandler = messageEventHandler;
    }

    @RequestMapping("/s")
    public void s() {
        messageEventHandler.sendBroadcast();
    }
}