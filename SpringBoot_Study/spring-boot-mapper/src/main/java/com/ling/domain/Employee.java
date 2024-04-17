package com.ling.domain;

import lombok.Data;


@Data
public class Employee {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)                 // 插入时主键返回，使用mysql自增
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;

}
