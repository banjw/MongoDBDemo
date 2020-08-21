package com.learn.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    public @Bean MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "test");
    }

    public @Bean MongoClient mongoClient() {
        return MongoClients.create(MongoClientSettings
                .builder()
                //链接地址
                .applyConnectionString(new ConnectionString("mongodb://10.10.10.102:37017"))
                //用户名密码
                .credential(MongoCredential.createCredential("test", "test", "123456".toCharArray()))
                .build());
    }
}
