package com.learn.demo;

import com.learn.demo.common.RemoveDollarOperation;
import com.learn.demo.dao.DemoInfoDao;
import com.learn.demo.entity.DemoInfo;
import com.learn.demo.entity.Department;
import com.learn.demo.entity.Employee;
import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Slf4j
class MongodbdemoApplicationTests {

    @Autowired
    private DemoInfoDao demoInfoDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindAll(){
        List<DemoInfo> all = demoInfoDao.findAll();
        all.forEach(demoInfo -> log.info(demoInfo.toString()));
    }

    @Test
    public void initData() {

        // 部门
        Department department = new Department();
        department.setDepartmentName("XXX信息开发系统");
        department.setEmployeeList(Collections.emptyList());
        mongoTemplate.save(department);

        // 员工
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmployeeName("张一");
        employee1.setPhone("159228359xx");
        employee1.setDepartment(department);
        employeeList.add(employee1);

        Employee employee2 = new Employee();
        employee2.setEmployeeName("张二");
        employee2.setPhone("159228358xx");
        employee2.setDepartment(department);
        employeeList.add(employee2);
        mongoTemplate.insert(employeeList, Employee.class);

        department.setEmployeeList(employeeList);
        mongoTemplate.save(department);
    }

    @Test
    public void testFindEmp(){
        Field from = Fields.field("department");
        Field localField = Fields.field("new_department.id");
        Field foreignField = Fields.field("_id");
        Field as = Fields.field("department_info");
        RemoveDollarOperation removeDollarOperation = new RemoveDollarOperation("new_department", "department");
        AggregationOperation aggregationOperation = new LookupOperation(from, localField, foreignField, as);
        Aggregation aggregation = Aggregation.newAggregation(removeDollarOperation, aggregationOperation);
        AggregationResults<BasicDBObject> aggregate = mongoTemplate.aggregate(aggregation, Employee.class, BasicDBObject.class);
        aggregate.forEach(basicDBObject -> {
            System.out.println(basicDBObject);
        });
    }
}
