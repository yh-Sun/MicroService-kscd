package com.sun.study.netty_socketio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NettySocketIOController {

    @RequestMapping("/netty")
    public String chat() {
        return "netty";
    }

}