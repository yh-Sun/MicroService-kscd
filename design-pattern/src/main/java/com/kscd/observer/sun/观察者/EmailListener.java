package com.kscd.observer.sun.观察者;

public class EmailListener implements Listener{

    private String email;

    public EmailListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String type, String msg) {
        System.out.println("邮件：当前处于状态" + type + ", 打印消息体" + msg + ", 邮箱是" + email);
    }
}
