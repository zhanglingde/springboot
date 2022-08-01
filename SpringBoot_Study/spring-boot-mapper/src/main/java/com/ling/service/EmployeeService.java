package com.ling.service;

import com.ling.domain.Employee;
import com.ling.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * 根据传入对象字段不为空作为where条件查询
     * @param condition
     * @return
     */
    public Employee getOne(Employee condition){
        Employee employee = employeeMapper.selectOne(condition);
        return employee;
    }

    /**
     * 根据主键查询
     * @param id @Id
     * @return
     */
    public Employee queryEmployeeById(Integer id){

        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
     * 插入
     * @param employee
     * @return
     */
    public int insert(Employee employee){
        return employeeMapper.insert(employee);
    }


    /**
     * 根据主键更新不为null的值
     * @param employee
     */
    public void updateByPrimaryKeySelective(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 删除，如果employee为空，就会删除表中全部数据，所以在使用该方法时需要判断一下
     * @param employee
     */
    public void removeEmployee(Employee employee) {
        employeeMapper.delete(employee);
    }

    /**
     * 根据主键删除
     * @param i
     */
    public void deleteByPrimaryKey(int i) {
        employeeMapper.deleteByPrimaryKey(i);
    }

    /**
     * 自定义 复杂条件查询
     * @param example
     * @return
     */
    public List<Employee> getEmpListByExample(Example example) {
        return employeeMapper.selectByExample(example);
    }
}
