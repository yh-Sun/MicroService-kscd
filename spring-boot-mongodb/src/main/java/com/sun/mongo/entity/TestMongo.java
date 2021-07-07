package com.sun.mongo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class TestMongo {

    private String id;

    private String x;

    private MongoA mA;

    @DBRef
    private List<MongoB> mongoBList;

    @Data
    public static class MongoA {
        private String x1;
    }

    @Data
    public static class MongoB {
        private String id;
        private String x2;
        private String v2;
        private String r2;
        private String sun2;
    }
}



