package com.sun.mongo.controller;

import com.sun.mongo.entity.TestMp;
import com.sun.mongo.mapper.TestMpMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestMpController {
    
    @Resource
    private TestMpMapper testMpMapper;

    
    /**
     * http://localhost:8080/query_test_mp?id=1
     * 
     * @author  Sun
     * @date    2021/6/28 17:30
     **/
    @GetMapping("query_test_mp")
    public String queryTestMp(String id) {
        TestMp testMp = testMpMapper.selectById(id);
        return testMp.toString();
    }
    
}
