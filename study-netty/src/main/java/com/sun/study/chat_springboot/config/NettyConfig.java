package com.sun.study.chat_springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyConfig {

    /** netty启动端口 */
    private int port;

    /** webSocket访问路径 */
    private String path;

}
