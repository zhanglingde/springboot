package com.ling;

import com.ling.domain.Employee;
import com.ling.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

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
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void test01(){
        Employee condition = new Employee(null,"bob",5560.11,null);
        Employee employee = employeeService.getOne(condition);

        System.out.println(employee);
    }

    @Test
    public void test02(){
        Employee employee = employeeService.queryEmployeeById(3);
        System.out.println(employee);
    }

    @Test
    public void test03(){
        Employee employee = new Employee();
        employee.setEmpName("zhangsan2");
        // employee.setEmpAge(18);
        employee.setEmpSalary(3000.2);
        employeeService.insert(employee);

        System.out.println("新增成功："+employee.getEmpId());
    }

    /**
     * 根据主键更新不为null的值
     */
    @Test
    public void testUpdateByPrimaryKeySelective(){
        Employee employee = new Employee();
        employee.setEmpId(8);
        employee.setEmpSalary(2000.00);
        employeeService.updateByPrimaryKeySelective(employee);
    }

    @Test
    public void delete(){
        Employee employee = null;
        employeeService.removeEmployee(employee);
    }

    @Test
    public void deleteByPrimaryKey(){
        employeeService.deleteByPrimaryKey(7);
    }

    @Test
    public void test(){
        //目标：WHERE (emp_salary>? AND emp_age<?) OR (emp_salary<? AND emp_age>?)

        Example example = new Example(Employee.class);
        //-----------------------------------------------------------------
        //设置排序信息
        example.orderBy("empSalary").asc().orderBy("empAge").desc();
        // 设置去重
        example.setDistinct(true);
        // 设置select的字段
        example.selectProperties("empName","empSalary");

        //通过Example对象创建Criteria对象
        Example.Criteria criteria1 = example.createCriteria();
        Example.Criteria criteria2 = example.createCriteria();
        //-------------------------------------------------------------------

        //设置条件 emp_salary > 3000 and emp_age < 25
        criteria1.andGreaterThan("empSalary",3000)
                .andLessThan("empAge",25);
        // 设置查询条件 emp_salary < 5000 and emp_age > 30
        criteria2.andLessThan("empSalary",5000)
                .andGreaterThan("empAge",30);

        // 4. 使用OR关键字组装两个Criteria对象
        example.or(criteria2);

        List<Employee> empList = employeeService.getEmpListByExample(example);
        for (Employee employee : empList) {
            System.out.println(employee);
        }

    }

}
