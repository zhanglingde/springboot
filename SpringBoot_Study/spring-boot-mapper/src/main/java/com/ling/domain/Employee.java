package com.ling.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "table_emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)                 // 插入时主键返回，使用mysql自增
    private Integer empId;
    private String empName;
    // @Column(name = "emp_salary_apple")
    private Double empSalary;   // 如果表中字段是emp_salary_apple
    private Integer empAge;

    public Employee() {

    }

    public Employee(Integer empId, String empName, Double empSalary, Integer empAge) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
    }
}
