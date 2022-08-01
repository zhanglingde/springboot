package com.ling.service.impl;

import com.ling.bean.User;
import com.ling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    RedisTemplate<String ,String> redisTemplate;

    @Resource(name = "redisTemplate")
    HashOperations<String, Integer, User> hash;

    @Resource(name = "redisTemplate")
    ListOperations<String, String> list;


    @Override
    public String getString(String key) {
        //获得操作redis字符串类型的对象
        ValueOperations<String, String> string = redisTemplate.opsForValue();
        string.set("java","这是一个测试数据",2, TimeUnit.HOURS);
        if(redisTemplate.hasKey(key)){
            // 从redis中查询key的值
            System.out.println("在Redis中查询返回");
            return string.get(key);
        }else {
            String result = "数据库中查询得数据";
            redisTemplate.opsForValue().set(key,result);
            System.out.println("MySql数据库中查询返回");
            return result;
        }
    }

    @Override
    public void add(User u) {

        redisTemplate.opsForHash().put("user",u.getId(),u);
    }

    @Override
    public User selectById(int id) {
        // 判断id在user的key中是否存在
        boolean flag = redisTemplate.opsForHash().hasKey("user",id);
        if(flag){
            // 存在，从Redis中查询
            User user = (User) redisTemplate.opsForHash().get("user",id);
            System.out.println("从redis中查询出对象："+user);
            return user;
        }else {
            // 模拟从数据库中查询
            User user = new User();
            user.setId(id);
            user.setName("Ling");
            System.out.println("从Mysql数据库中查询:"+user);
            hash.put("user",id,user);
            return user;
        }

    }

    @Override
    public void listAdd() {
        String key = "news:top10";
        redisTemplate.opsForList().leftPush(key,"清明追思 家国永念丨那些应被铭记的战“疫”瞬间");
        list.leftPush(key,"aa");
        redisTemplate.opsForList().leftPushAll(key,"bbb","ccc","ddd","eee");
    }

    /**
     * List 查询分页
     * @return
     */
    @Override
    public List<String> listRange(int pageNum,int pageSize) {
        String key = "news:top10";
        /**
         *  pageNum:页数
         *  pageSize:每页显示数
         */
        int start = (pageNum-1)*pageSize;
        int stop = pageSize*pageNum-1;
        List<String> newsList = redisTemplate.opsForList().range(key,start,stop);
        return newsList;
    }

    /**
     * List 任务队列，订单流程示例
     * 用户付款后，根据用户的收货地址和商家发货地址生成 一个队列（任务）
     */
    public void listQueueInit(String cardId){
        String key = "prod:"+cardId;        // 初始化key
        list.leftPushAll(key,"1.商家出货","2.快递发件","3.北京某小区-->首都机场","4.北京机场-->南京机场","5.南京机场--> 南京某小区","6.用户收货");

    }

    // 触发事件
    public void listQueueTouch(String cardId){
        String key = "prod:"+cardId+":succ";
        redisTemplate.opsForList().rightPopAndLeftPush("prod:"+cardId,key);
    }
    // 客户查询：快递到哪了
    public List<String> listQueueSucc(String cardId){
        String key = "prod:"+cardId+":succ";
        return redisTemplate.opsForList().range(key , 0,-1);
    }
    // 物流查询：当前快递还有多少任务没有执行
    public List<String> listQueueWait(String cardId){
        String key = "prod:"+cardId;
        return list.range(key,0 , -1);
    }
}
