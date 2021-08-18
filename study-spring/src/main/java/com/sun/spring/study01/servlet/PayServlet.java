package com.sun.spring.study01.servlet;

import com.sun.spring.study01.service.PayService;
import com.sun.spring.study01.service.impl.PayServiceImpl;

public class PayServlet {

    private PayService service = new PayServiceImpl();

    /**
     * 买家-1块钱、卖家+1块钱
     */
    public void doGet(String name) {
        service.pay(name);
    }

    public static void main(String[] args) {
        PayServlet servlet = new PayServlet();
        servlet.doGet("sun");
    }
}
