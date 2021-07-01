package com.sun.mongo;

import cn.hutool.json.JSONUtil;
import com.sun.mongo.entity.TestMongo;
import com.sun.mongo.service.TestMongoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    @Resource
    private TestMongoService mongoService;
    
    
    /**
     * 测试mongo的demo
     **/
    @Test
    public void testMg() {
        TestMongo mongo = new TestMongo();
        mongo.setX("dfadskfjalskfsakjfkls");
        TestMongo.MongoA mongoA = new TestMongo.MongoA();
        mongoA.setX1("x1");
        TestMongo.MongoB mongoB = new TestMongo.MongoB();
        mongoB.setSun2("孙大帅");
        mongoB.setR2("rrrr");
        mongoB.setX2("xxxx");
        mongoB.setV2("vvvv");
        TestMongo.MongoB mongoB1 = new TestMongo.MongoB();
        mongoB1.setSun2("孙大帅1");
        mongoB1.setR2("rrrr1");
        mongoB1.setX2("xxxx1");
        mongoB1.setV2("vvvv1");
        
        mongo.setMA(mongoA);
//        mongo.setMongoBList(CollUtil.newArrayList(mongoB, mongoB1));
        mongoService.save(mongo);

        List<TestMongo> all = mongoService.findAll();
        System.out.println(JSONUtil.toJsonStr(all));
    }
    
}
