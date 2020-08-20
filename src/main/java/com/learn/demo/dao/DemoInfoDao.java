package com.learn.demo.dao;

import com.learn.demo.entity.DemoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoInfoDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<DemoInfo> findAll(){
        return mongoTemplate.findAll(DemoInfo.class);
    }
}
