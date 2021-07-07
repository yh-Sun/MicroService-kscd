package com.sun.mongo.service.impl;

import com.sun.mongo.entity.TestMongo;
import com.sun.mongo.service.TestMongoService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestMongoServiceImpl implements TestMongoService {

    private final MongoTemplate mongoTemplate;

    public TestMongoServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(TestMongo mongo) {
        mongoTemplate.save(mongo);
    }

    @Override
    public List<TestMongo> findAll() {
        return mongoTemplate.findAll(TestMongo.class);
    }

    @Override
    public TestMongo findById(String id) {
        return mongoTemplate.findById(id, TestMongo.class);
    }

}
