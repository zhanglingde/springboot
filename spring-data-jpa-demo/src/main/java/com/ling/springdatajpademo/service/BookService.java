package com.ling.springdatajpademo.service;

import com.ling.springdatajpademo.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    // 更新或删除需要在事务中，查询事 Spring Data 自动加了只读事务
    @Transactional
    public void updateBookById(String name, Long id){
        bookDao.updateBookById(name,id);
    }
}
