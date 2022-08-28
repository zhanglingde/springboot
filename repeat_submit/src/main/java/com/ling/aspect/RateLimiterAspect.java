package com.ling.aspect;

import com.ling.annotation.LimitType;
import com.ling.annotation.RateLimiter;
import com.ling.exception.RateLimitException;
import com.ling.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Collections;

@Aspect
@Component
public class RateLimiterAspect {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterAspect.class);

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    RedisScript<Long> redisScript;


    @Before("@annotation(rateLimiter)")
    public void before(JoinPoint jp, RateLimiter rateLimiter) throws RateLimitException {
        int time = rateLimiter.time();
        int count = rateLimiter.count();
        String combineKey = getCombineKey(rateLimiter, jp);
        try {
            // 执行 lua 脚本，第二个参数是 key 的集合，后续是参数是 value
            Long number = redisTemplate.execute(redisScript, Collections.singletonList(combineKey), time, count);
            if (number == null || number.intValue() > count) {
                // 超过限流阈值
                logger.info("当前接口以达到最大限流次数");
                throw new RateLimitException("访问过于频繁，请稍后访问");
            }
            logger.info("一个时间窗内请求次数：{}，当前请求次数：{}，缓存的 key 为 {}", count, number, combineKey);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 这个 key 其实就是接口调用次数缓存在 redis 的 key
     * rate_limit:11.11.11.11-org.javaboy.ratelimit.controller.HelloController-hello
     * rate_limit:org.javaboy.ratelimit.controller.HelloController-hello
     * @param rateLimiter
     * @param jp
     * @return
     */
    private String getCombineKey(RateLimiter rateLimiter, JoinPoint jp) {
        StringBuffer key = new StringBuffer(rateLimiter.key());
        if (rateLimiter.limitType() == LimitType.IP) {
            key.append(IpUtils.getIpAddr(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()))
                    .append("-");
        }
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        key.append(method.getDeclaringClass().getName())
                .append("-")
                .append(method.getName());
        return key.toString();
    }
}