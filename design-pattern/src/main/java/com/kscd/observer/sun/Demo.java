package com.kscd.observer.sun;

import com.kscd.observer.sun.发布者.OrderStatus发布者;
import com.kscd.observer.sun.观察者.EmailListener;
import com.kscd.observer.sun.观察者.LogListener;
import com.kscd.observer.sun.观察者.WeChatListener;

/**
 * 场景：状态 1 2 3
 * 状态1：发邮件、打印日志、发微信
 * 状态2：发邮件
 * 状态3：打印日志
 */
public class Demo {

    public static void main(String[] args) {
        OrderStatus发布者 status = new OrderStatus发布者();
        status.基础发布者.add("1", new EmailListener("sunyuanhe_i@163.com"));
        status.基础发布者.add("1", new LogListener());
        status.基础发布者.add("1", new WeChatListener());
        status.基础发布者.add("2", new EmailListener("sunyuanhe_i@163.com"));
        status.基础发布者.add("3", new LogListener());

        status.状态1("我是状态·1的消息·1");
        status.状态2("我是状态·2的消息·2");
        status.状态3("我是状态·3的消息·3");
    }

}
