package com.ling.service;

import com.ling.bean.User;

import java.util.List;

public interface UserService {

    /**
     * 根据key得到string
     * @param key
     * @return
     */
    public String getString(String key);


    /**
     * redis存储 hash数据
     * @param u
     */
    public void add(User u);

    /**
     * 根据id查询 hash
     * @param id
     * @return
     */
    public User selectById(int id);

    /**
     * list类型 添加
     */
    public void listAdd();
    public List<String> listRange(int pageNum,int pageSize);
}
