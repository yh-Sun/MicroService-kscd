package com.sun.mongo;

import com.sun.mongo.entity.TestMp;
import com.sun.mongo.mapper.TestMpMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Resource
    private TestMpMapper testMpMapper;
    
    
    /**
     * 测试mybatis-plus的demo
     **/
    @Test
    public void testMp() {
        TestMp testMp = testMpMapper.selectById(1);
        System.out.println(testMp.toString());
    }
    
}
