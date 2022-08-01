package com.ling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.ling.mapper")              // 配置mybatis要扫描的mapper包
@SpringBootApplication
@EnableCaching
public class Sp1CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp1CacheApplication.class, args);
    }

}
