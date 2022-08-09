package com.limg.mongodb.test03;

import com.limg.mongodb.test03.service.FruitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MongoDbTransactionsTest{

    @Autowired
    FruitService fruitService;

    @Test
    public void test() {
        fruitService.insert();
    }
}
