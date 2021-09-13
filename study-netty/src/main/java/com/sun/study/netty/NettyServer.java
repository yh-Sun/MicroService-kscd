package com.sun.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;

/**
 * Netty服务端
 */
public class NettyServer {

    @SneakyThrows
    public static void main(String[] args) {
        // 1.创建bossGroup线程组，处理网络事件：连接事件
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 2.创建workerGroup线程组，处理网络事件：读写事件
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 3.创建服务端启动助手
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 4.设置bossGroup线程组和workerGroup线程组
        serverBootstrap.group(bossGroup, workerGroup)
                // 5.设置服务器通道实现为NIO
                .channel(NioServerSocketChannel.class)
                // 6.参数设置
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                // 7.创建一个通道初始化对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        // 8.向pipeline中添加自定义处理handler
                        socketChannel.pipeline().addLast(new NettyServerHandler());
                    }
                });
        // 9.启动服务端并绑定端口，同时将异步改为同步
        ChannelFuture future = serverBootstrap.bind(9999).sync();
        // 10.关闭通道和关闭连接池
        future.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}
