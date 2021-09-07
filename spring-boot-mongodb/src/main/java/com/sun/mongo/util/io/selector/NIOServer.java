package com.sun.mongo.util.io.selector;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * 选择器-服务端
 *
 * @author  Sun
 * @date    2021/9/7 16:00
 * @since   1.0
 */
public class NIOServer {

    @SneakyThrows
    public static void main(String[] args) {
        // 1.打开服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2.绑定对应的端口
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 3.通道默认是阻塞的，需要调整为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 4.创建选择器
        Selector selector = Selector.open();
        // 5.将服务端通道注册到选择器上，并指定注册监听的时间为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动成功");

        while (true) {
            // 6.检测选择器是否有事件
            int select = selector.select(5000);
            if(select == 0) {
                System.out.println("没有事件发生...");
            }
            // 7.获取事件集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                // 8.判断事件是否是客户端连接事件SelectionKey.isAcceptable()
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()) {
                    // 9.得到客户端通道，并将客户端通道注册到选择器上，并指定监听时间为OP_READ
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("有客户端连接");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }

                // 10.判断是否是客户读就绪事件SelectionKey.isReadable()
                if(selectionKey.isReadable()) {
                    // 11.得到客户端通道，读取数据到缓冲区
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    int read = socketChannel.read(allocate);
                    if(read > 0) {
                        System.out.println("读取到客户端数据" + new String(allocate.array(), 0, read, StandardCharsets.UTF_8));
                    }
                    // 12.给客户端回写数据
                    socketChannel.write(ByteBuffer.wrap("没钱".getBytes(StandardCharsets.UTF_8)));
                    socketChannel.close();
                }
                // 13.从集合中删除对应的事件，因为防止二次处理
                iterator.remove();
            }
        }
    }

}
