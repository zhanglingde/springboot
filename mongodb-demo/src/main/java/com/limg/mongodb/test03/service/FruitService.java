package com.limg.mongodb.test03.service;

import com.limg.mongodb.test03.entity.Apple;
import com.limg.mongodb.test03.entity.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FruitService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void insert() {
        Apple apple = new Apple("红色", 10);
        Fruit fruit = new Fruit("黄色", "香蕉");
        mongoTemplate.insert(apple);
        mongoTemplate.insert(fruit);
        System.out.println(1 / 0);
    }
}
