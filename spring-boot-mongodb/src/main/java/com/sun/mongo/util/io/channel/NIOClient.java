package com.sun.mongo.util.io.channel;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * 客户端
 *
 * @author  Sun
 * @date    2021/9/7 15:25
 * @since   1.0
 */
public class NIOClient {

    @SneakyThrows
    public static void main(String[] args) {
        // 打开通道
        SocketChannel channel = SocketChannel.open();
        // 设置IP和端口号，连接服务端
        channel.connect(new InetSocketAddress("127.0.0.1", 9999));
        // 写出数据
        channel.write(ByteBuffer.wrap("我(客户端)告诉你一个重要的消息".getBytes(StandardCharsets.UTF_8)));
        // 读取服务器写回的数据
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = channel.read(allocate);
        System.out.println("服务端的响应" + new String(allocate.array(), 0, read, StandardCharsets.UTF_8));
        // 释放资源
        channel.close();
    }

}
