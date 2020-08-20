package com.learn.demo;

import com.learn.demo.dao.DemoInfoDao;
import com.learn.demo.entity.DemoInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class MongodbdemoApplicationTests {

    @Autowired
    private DemoInfoDao demoInfoDao;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindAll(){
        List<DemoInfo> all = demoInfoDao.findAll();
        all.forEach(demoInfo -> log.info(demoInfo.toString()));
    }
}
