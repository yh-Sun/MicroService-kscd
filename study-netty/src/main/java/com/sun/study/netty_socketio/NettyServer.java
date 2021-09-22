package com.sun.study.netty_socketio;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class NettyServer {

    // 客户端socket链接
    private static List<SocketIOClient> clients = new ArrayList<>();

    @SneakyThrows
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("127.0.0.1");
        config.setPort(9100);
        // 服务
        SocketIOServer server = new SocketIOServer(config);
        // 创建链接监听
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                clients.add(client);
            }
        });
        // 断开链接监听
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                clients.remove(client);
            }
        });

        // 事件处理
        server.addEventListener("netty", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) throws Exception {
                server.getBroadcastOperations().sendEvent("netty", data);
            }
        });

        // 启动服务
        server.start();

        System.out.println("开始推送");
        while (true) {
            if(CollUtil.isNotEmpty(clients)) {
                System.out.println("有人了");

                for (SocketIOClient client : clients) {
                    ChatObject data = new ChatObject();
                    data.setMessage("你好" + DateUtil.date());
                    data.setUserName("小太阳服务端");
                    client.sendEvent("netty", data);
                }
            }

            Thread.sleep(5000);
        }

    }

}
