package com.ling;

import com.ling.bean.User;
import com.ling.utils.JedisPoolUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RedisDemo {

    @Test
    void contextLoads() {
        // 测试连接Redis是否成功
        Jedis jedis = new Jedis("192.168.0.107",6379);
        System.out.println(jedis.ping());
    }

    /**
     * 测试字符串
     */
    @Test
    public void test02(){
        Jedis jedis = new Jedis("192.168.0.107",6379);

        String key = "applicationName";
        if(jedis.exists(key)){
            String result = jedis.get(key);
            System.out.println("Redis数据库查询得到:"+result);
        }else{
            // 模拟从数据库中查询
            String result = "微信开发";
            jedis.set(key,result);
            System.out.println("MySql数据库中查询得到:"+result);
        }

        jedis.close();
    }

    @Test
    public void hashTest(){
        // 通过连接池获得jedis客户端
        Jedis jedis = JedisPoolUtil.getJedis();
        int id = 3;
        String key = User.getKeyName()+id;  // user:1
        if(jedis.exists(key)){
            // 从Redis中查询
            Map<String,String> map = jedis.hgetAll(key);
            User u = new User();
            u.setId(Integer.valueOf(map.get("id")));
            u.setName(map.get("name"));
            u.setAge(Integer.parseInt(map.get("age")));

            System.out.println("Redis中查询得: "+u);
        }else{
            // 模拟从数据库中查询
            User u = new User();
            u.setId(id);u.setName("李四");u.setAge(18);
            // 存入redis中
            Map<String,String> map = new HashMap<>();
            map.put("id",u.getId().toString());
            map.put("name",u.getName());
            map.put("age",u.getAge().toString());
            System.out.println("Mysql数据库中查询得: "+u);
            jedis.hmset(key,map);

        }

        JedisPoolUtil.close(jedis);
    }


}
