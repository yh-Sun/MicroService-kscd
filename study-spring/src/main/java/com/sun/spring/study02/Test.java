package com.sun.spring.study02;

import cn.hutool.json.JSONUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans-02.xml");
        MyBean myBean = (MyBean) applicationContext.getBean("myBean");
        System.out.println(JSONUtil.toJsonStr(myBean));

        // &：直接获取工厂
        MyFactoryBean myFactoryBean = (MyFactoryBean) applicationContext.getBean("&myBean");
        System.out.println(JSONUtil.toJsonStr(myFactoryBean));
    }
}
