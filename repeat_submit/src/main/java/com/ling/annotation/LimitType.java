package com.ling.annotation;

/**
 * 限流的类型
 */
public enum LimitType {

    /**
     * 默认的限流策略，针对某一个接口进行限流
     */
    DEFAULT,
    /**
     * 针对某一个 IP 进行限流
     */
    IP
}