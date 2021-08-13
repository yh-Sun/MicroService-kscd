package com.kscd.observer.sun.发布者;

public class OrderStatus发布者 {

    public 基础发布者 基础发布者;

    public OrderStatus发布者() {
        this.基础发布者 = new 基础发布者("1", "2", "3");
    }

    // 下面就是不同状态需要发布的行为
    public void 状态1(String msg) {
        基础发布者.notify("1", msg);
    }

    public void 状态2(String msg) {
        基础发布者.notify("2", msg);
    }

    public void 状态3(String msg) {
        基础发布者.notify("3", msg);
    }


}
