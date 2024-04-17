package com.ling.mapper;


import com.ling.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
// public interface EmployeeMapper extends Mapper<Employee> {
public interface EmployeeMapper{


    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectList();
}
