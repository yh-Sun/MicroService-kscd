package com.sun.mongo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.sun.mongo.entity.TestMongo;
import com.sun.mongo.service.TestMongoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    @Resource
    private TestMongoService mongoService;

    @Resource
    private MongoTemplate mongoTemplate;


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
        mongoB.setId(UUID.randomUUID().toString());
        mongoB.setSun2("孙大帅");
        mongoB.setR2("rrrr");
        mongoB.setX2("xxxx");
        mongoB.setV2("vvvv");
        TestMongo.MongoB mongoB1 = new TestMongo.MongoB();
        mongoB1.setId(UUID.randomUUID().toString());
        mongoB1.setSun2("孙大帅1");
        mongoB1.setR2("rrrr1");
        mongoB1.setX2("xxxx1");
        mongoB1.setV2("vvvv1");

        mongo.setMA(mongoA);
        mongo.setMongoBList(CollUtil.newArrayList(mongoB, mongoB1));
        mongoService.save(mongo);

        List<TestMongo> all = mongoService.findAll();
        System.out.println(JSONUtil.toJsonStr(all));

        mongoTemplate.insert(mongo.getMongoBList(), TestMongo.MongoB.class);
    }


    /**
     * 测试根据id查询mongo数据
     **/
    @Test
    public void selectById() {
        String id = "60e41fa6841f7542ecd371df";
        TestMongo mongo = mongoService.findById(id);
        System.out.println(JSONUtil.toJsonStr(mongo));
    }

    @Test
    public void findByMongoId() {
        // 根据test_mongo集合中的DbRef id查询
        {
            Query query = new Query();
            query.addCriteria(Criteria.where("mongoBList.$id").is("9a412e75-0bcc-4024-a7a3-49646e78d034"));
            List<TestMongo> testMongos = mongoTemplate.find(query, TestMongo.class);
            System.out.println(JSONUtil.toJsonStr(testMongos));
        }

        // 根据test_mongo关联集合mongoB的x2查询
        {
            Query query = new Query();
            query.addCriteria(Criteria.where("mongoBList.$x2").is("xxxx"));
            List<TestMongo> testMongos = mongoTemplate.find(query, TestMongo.class);
            System.out.println(JSONUtil.toJsonStr(testMongos));
        }

        // 根据test_mongo关联集合mongoB的x2查询：联合查询 TODO:
        /*
         * https://blog.csdn.net/zhang135123/article/details/85273957
         * https://www.cnblogs.com/xuliuzai/p/10055535.html
         * https://blog.csdn.net/bokestudy/article/details/103702824
         */
        {
            LookupOperation as = LookupOperation.newLookup().from("mongoBList").localField("id").foreignField("id").as("inventory_docs");
            MatchOperation matchOperation = new MatchOperation(Criteria.where("inventory_docs.x2").is("xxxx"));
            List<TestMongo> mappedResults = mongoTemplate.aggregate(Aggregation.newAggregation(TestMongo.class, as, matchOperation), TestMongo.class).getMappedResults();
            System.out.println(mappedResults);
        }
    }

}
