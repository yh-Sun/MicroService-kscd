package com.sun.spring.study01.dao;

import com.sun.spring.study01.pojo.Pay;

public interface PayDao {

    Pay queryPayByName(String name);

    void save(Pay pay);

}
