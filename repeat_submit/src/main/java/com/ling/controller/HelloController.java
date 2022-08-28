package com.ling.controller;

import cn.hutool.json.JSONUtil;
import com.ling.annotation.RepeatSubmit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/hello")
    @RepeatSubmit(interval = 10000)
    public String hello(@RequestBody User user) {
        return JSONUtil.toJsonStr(user);
    }

    @PostMapping("/hello2")
    public String hell2(String key) {
        return key;
    }
}