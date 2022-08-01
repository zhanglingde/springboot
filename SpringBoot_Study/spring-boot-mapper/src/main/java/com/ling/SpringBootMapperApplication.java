package com.ling;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ling.mapper")
public class SpringBootMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMapperApplication.class, args);
    }

}
