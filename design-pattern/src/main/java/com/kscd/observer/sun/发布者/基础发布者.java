package com.kscd.observer.sun.发布者;

import com.kscd.observer.sun.观察者.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拆出来一个基础发布者，应该是为了多种类型的发布者使用，订单状态new 基础发布者()、保单状态也new()
 */
public class 基础发布者 {

    public Map<String, List<Listener>> listeners = new HashMap<>();

    /**
     * 定义这个方法主要为了创建发布者时，确认好了几种需要观察的类型，本例中：状态1 状态2 状态3
     */
    public 基础发布者(String... types) {
        for (String type : types) {
            this.listeners.put(type, new ArrayList<>());
        }
    }

    public void add(String type, Listener listener) {
        List<Listener> users = this.listeners.get(type);
        users.add(listener);
    }

    public void remove(String type, Listener listener) {
        List<Listener> users = this.listeners.get(type);
        users.remove(listener);
    }

    public void notify(String type, String msg) {
        List<Listener> users = this.listeners.get(type);
        for (Listener user : users) {
            user.update(type, msg);
        }
    }


}
