package com.sun.spring.study01.service.impl;

import com.sun.spring.study01.dao.PayDao;
import com.sun.spring.study01.dao.impl.PayDaoImpl;
import com.sun.spring.study01.pojo.Pay;
import com.sun.spring.study01.service.PayService;

public class PayServiceImpl implements PayService {

    private PayDao dao = new PayDaoImpl();

    @Override
    public void pay(String name) {
        // 获取买家、kscd的账户
        Pay user = dao.queryPayByName(name);
        Pay kscd = dao.queryPayByName("kscd");

        // 买家-1块钱、卖家+1块钱
        user.setMoney(user.getMoney() - 1);
        kscd.setMoney(kscd.getMoney() + 1);

        user.setCount(user.getCount() + 1);
        kscd.setCount(kscd.getCount() + 1);

        // 保存
        dao.save(user);
        dao.save(kscd);
    }
}
