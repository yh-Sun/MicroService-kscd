package com.sun.mongo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.sun.mongo.mapper")
public class MybatisPlusConfig {

}
