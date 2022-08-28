package com.ling.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 * @author zhangling
 * @date 2021/12/17 5:29 下午
 */
@Component
@Slf4j
public class RedisUtils {
    RedisUtils() {
    }


    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 86400L;
    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1L;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    ValueOperations<String, String> valueOperations;
    @Autowired
    HashOperations<String, String, Object> hashOperations;
    @Autowired
    SetOperations<String, Object> setOperations;
    @Autowired
    ZSetOperations<String, Object> zSetOperations;

    /**
     * 判断 key 是否存在
     *
     * @param key key
     * @return true 存在; false 不存在
     */
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 设置 key 过期时间
     *
     * @param key     key
     * @param timeout 过期时间，单位：秒
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, JSONUtil.toJsonStr(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : JSONUtil.toBean(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除 支持模糊删除
     *
     * @param key goodsShare_*_2662
     */
    public void deleteBatch(String key) {
        Set<String> keys = redisTemplate.keys(key);
        redisTemplate.delete(keys);
    }


    /**
     * 将数据放入list
     *
     * @param key   键
     * @param value 值
     * @return 是否缓存成功
     */
    public boolean lSet(String key, Object value) {
        return lSet(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 将数据放入list
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 是否缓存成功
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.expire(key, DEFAULT_EXPIRE, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("redis 设置 list 数据错误", e);
            return false;
        }
    }

    /**
     * 将list数据放入list
     *
     * @param key   键
     * @param value 值
     * @return 是否缓存成功
     */
    public <T> boolean listSetArrayList(String key, List<T> value) {
        return listSetArrayList(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 将list数据放入list
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 是否缓存成功
     */
    public <T> boolean listSetArrayList(String key, List<T> value, long time) {
        try {
            // 集合不能存储到 Redis 的 List 中，会将集合当成一个元素，存储到 List 的一列，而不是多列
            T[] objects = (T[]) value.toArray();
            redisTemplate.opsForList().rightPushAll(key, objects);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.expire(key, DEFAULT_EXPIRE, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("redis 设置 list 数据错误", e);
            return false;
        }
    }


    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return 返回 List 缓存数据
     */
    public <T> List<T> lGet(String key, long start, long end) {
        try {
            return (List<T>) redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("redis 获取 list 内容错误", e);
            return Collections.emptyList();
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return 返回 List 类型 key 的长度
     */
    public long listSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("redis 获取 list 长度错误", e);
            return 0;
        }
    }

    /**
     * 移除并返回 list 尾部元素
     *
     * @param key 键
     * @param <T> 泛型
     * @return 返回移除的元素
     */
    public <T> T rightPop(String key) {
        try {
            return (T) redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            log.error("redis 获取尾部元素错误", e);
            return null;
        }
    }

    /**
     * 队列
     */
    public void convertAndSend(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }

    /**
     * hmSet 操作
     *
     * @param mapKey Hash key
     * @param param  参数 field-value
     */
    public void hPutAll(String mapKey, Map<String, String> param) {
        hashOperations.putAll(mapKey, param);
        redisTemplate.expire(mapKey, DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }


    /**
     * hSet 操作
     *
     * @param mapKey   键
     * @param key      属性
     * @param paramMap 值
     */
    public void hSet(String mapKey, String key, String paramMap) {
        hashOperations.put(mapKey, key, paramMap);
        redisTemplate.expire(mapKey, DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    /**
     * hSet 操作
     *
     * @param mapKey   键
     * @param key      属性
     * @param paramMap 值
     * @param expire   过期时间
     */
    public void hSet(String mapKey, String key, String paramMap, long expire) {
        hashOperations.put(mapKey, key, paramMap);
        redisTemplate.expire(mapKey, expire, TimeUnit.SECONDS);
    }

    /**
     * hGet操作
     *
     * @param mapKey 键
     * @param key    属性
     * @return 返回 Hash 类型key中属性对应的值
     */
    public Object hGet(String mapKey, Object key) {
        return hashOperations.get(mapKey, key);
    }

    /**
     * hmGet 操作
     *
     * @param mapKey Hash key
     * @param params 参数 field-value
     * @return 返回 Hash 类型key中属性对应的值
     */
    public List<String> hmGet(String mapKey, Collection params) {
        return hashOperations.multiGet(mapKey, params);
    }

    /**
     * Set 将一个或多个元素添加到给定集合里面，已经存在于集合中的元素会自动被忽略, 命令返回新添加到集合的元素数量
     *
     * @param key 键
     * @param var Set的值，可以是一个元素，也可以是多个元素的数组
     * @return 是否缓存成功
     */
    public boolean sAdd(String key, String... var) {
        return sAdd(key, DEFAULT_EXPIRE, var);
    }

    /**
     * Set 将一个或多个元素添加到给定集合里面，已经存在于集合中的元素会自动被忽略, 命令返回新添加到集合的元素数量
     *
     * @param key  键
     * @param time 过期时间
     * @param var  Set的值，可以是一个元素，也可以是多个元素的数组
     * @return 是否缓存成功
     */
    public boolean sAdd(String key, long time, String... var) {
        try {
            setOperations.add(key, var);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.expire(key, DEFAULT_EXPIRE, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("Redis sAdd 错误", e);
            return false;
        }
    }

    /**
     * Set 查询Set
     *
     * @param key 键
     * @return 返回集合中的所有元素
     */
    public Set<Object> sMembers(String key) {
        return setOperations.members(key);
    }

    /**
     * ------------------------zset相关操作----------------------------
     */

    /***
     *  zSet 操作
     */
    public boolean zAdd(String key, String time, Double score) {
        return Boolean.TRUE.equals(zSetOperations.add(key, time, score));
    }

    /**
     * 限流方法（简单限流，不适合数据量大的限流）
     * @param user 操作的用户，相当于是限流的对象
     * @param action 具体的操作
     * @param period 时间窗，限流的周期(单位s)
     * @param maxCount 限流的次数
     * @return 是否允许操作
     */
    public boolean isAllowed(String user, String action, int period, int maxCount) {
        // 1.数据用 zset 保存，zset 的 key
        String key = user + "-" + action;
        long nowTime = System.currentTimeMillis();
        // 3.建立管道
        List<Object> pipelinedResultList = redisTemplate.executePipelined(new SessionCallback<Object>() {
            public <K,V> Long execute(RedisOperations<K, V> operations) throws DataAccessException {
                ZSetOperations<String, Object> zSetOperations1 = (ZSetOperations<String, Object>) operations.opsForZSet();
                operations.multi();
                zSetOperations1.add(key, nowTime, Convert.toDouble(nowTime));
                zSetOperations1.removeRangeByScore(key, 0, nowTime - period * 1000);
                Long response = zSetOperations1.zCard(key);
                operations.exec();
                return response;
            }
        });

        return Convert.toLong(((List)pipelinedResultList.get(0)).get(2)) <= maxCount;
        // return Convert.toLong(pipelinedResultList.get(2)) <= maxCount;
    }


}
