package com.sun.spring.study02;

import org.springframework.beans.factory.FactoryBean;


/**
 * 创建复杂Bean的工厂
 *
 * @author  Sun
 * @date    2021/8/18 11:35
 * @since   1.0
 */
public class MyFactoryBean implements FactoryBean<MyBean> {

    private String list;

    public void setList(String list) {
        this.list = list;
    }

    @Override
    public MyBean getObject() throws Exception {
        MyBean myBean = new MyBean();
        String[] split = list.split(",");
        myBean.setName(split[0]);
        myBean.setAge(split[1]);
        myBean.setSex(split[2]);
        System.out.println("我是工厂，我啥都会");
        return myBean;
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
