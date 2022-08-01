package com.ling;

import com.ling.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

@SpringBootTest
class Sp1RedisApplicationTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Test
    void contextLoads() {

        Jedis jedis = new Jedis("192.168.0.107",6379);
        System.out.println(jedis.ping());

        redisTemplate.opsForValue().set("strName","hello world" );

    }

    @Test
    public void testHash(User user){


        redisTemplate.opsForHash().put("user","4",user);
    }

}
