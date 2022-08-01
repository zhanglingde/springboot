package com.ling.services;

import com.ling.bean.Department;
import com.ling.bean.Employee;
import com.ling.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    CacheManager cacheManager;
    /**
     *  使用注解缓存
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id){
       System.out.println("查询部门"+id);
       Department department = departmentMapper.getDeptById(id);
       return department;
    }

    /**
     *  使用缓存管理器得到缓存，进行api调用
     * @param id
     * @return
     */
    public Department getDeptById2(Integer id){
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);

        // 缓存管理器得到缓存，然后操作redis
        Cache dept = cacheManager.getCache("dept");
        dept.put("dept:1",department);

        return department;
    }

}
