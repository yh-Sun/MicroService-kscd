package com.sun.mongo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "test_mp")
public class TestMp {
    
    @TableId
    private Long id;

    private String name;

    private Byte age;

    @Override
    public String toString() {
        return "TestMp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
