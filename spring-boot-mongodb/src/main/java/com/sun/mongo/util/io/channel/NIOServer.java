package com.sun.mongo.util.io.channel;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * 服务端
 *
 * @author  Sun
 * @date    2021/9/7 15:08
 * @since   1.0
 */
public class NIOServer {

    @SneakyThrows
    public static void main(String[] args) {
        // 1.打开服务端通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 2.绑定对应的端口号
        channel.bind(new InetSocketAddress(9999));
        // 3.通道默认是阻塞的，需要设置为非阻塞
        channel.configureBlocking(false);
        System.out.println("服务端启动成功");

        while (true) {
            // 4.检查是否有客户端连接，有客户端连接会返回相应的通道
            SocketChannel accept = channel.accept();
            if (accept == null) {
                System.out.println("没有客户端连接，我去忙别的事了");
                Thread.sleep(1000);
                continue;
            }
            // 5.获取客户端传递过来的数据，并把数据放在byteBuffer缓冲区中
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            /* read
             * 正数：读到有效字节
             * 0：没数据
             * -1：读到末尾
             */
            int read = accept.read(allocate);
            System.out.println("客户端消息" + new String(allocate.array(), 0, read, StandardCharsets.UTF_8));
            // 6.给客户端回写数据
            ByteBuffer writeBuffer = ByteBuffer.wrap("我是服务端，我收到了".getBytes(StandardCharsets.UTF_8));
            accept.write(writeBuffer);
            // 7.释放资源
            accept.close();
        }

    }

}
