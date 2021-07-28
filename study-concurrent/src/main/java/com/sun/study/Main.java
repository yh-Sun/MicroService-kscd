package com.sun.study;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

public class Main {

    public static void main(String[] args) {
        DateTime parse = DateUtil.parse("2021-04-22 12:30:23");
        System.out.println(parse);
    }

}
