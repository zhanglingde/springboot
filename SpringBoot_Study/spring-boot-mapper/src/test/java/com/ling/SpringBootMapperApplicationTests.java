package com.ling;

import com.ling.domain.Employee;
import com.ling.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SpringBootMapperApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    EmployeeService employeeService;

    @Test
    void contextLoads() throws SQLException {
        Employee employee = employeeService.selectById(1);
        System.out.println();
    }

    @Test
    void test01(){
        List<Employee> list = employeeService.selectList();
        System.out.println(list);
    }

}
