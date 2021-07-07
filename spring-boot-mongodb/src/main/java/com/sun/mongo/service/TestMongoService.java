package com.sun.mongo.service;

import com.sun.mongo.entity.TestMongo;

import java.util.List;

public interface TestMongoService {

    void save(TestMongo mongo);

    List<TestMongo> findAll();

    TestMongo findById(String id);

}
