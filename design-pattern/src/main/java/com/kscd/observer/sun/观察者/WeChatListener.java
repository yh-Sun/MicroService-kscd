package com.kscd.observer.sun.观察者;

public class WeChatListener implements Listener{

    @Override
    public void update(String type, String msg) {
        System.out.println("微信消息：当前处于状态" + type + ", 打印消息体" + msg);
    }
}
