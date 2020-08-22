package com.learn.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.username}")
    private String userName;
    @Value("${spring.data.mongodb.password}")
    private String password;

    public @Bean MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "test");
    }

    public @Bean MongoClient mongoClient() {
        return MongoClients.create(MongoClientSettings
                .builder()
                //链接地址
                .applyConnectionString(new ConnectionString(uri))
                //用户名密码
                .credential(MongoCredential.createCredential(userName, database, password.toCharArray()))
                .build());
    }
}
