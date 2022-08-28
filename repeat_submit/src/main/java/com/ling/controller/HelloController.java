package com.ling.controller;

import cn.hutool.json.JSONUtil;
import com.ling.annotation.RateLimiter;
import com.ling.annotation.RepeatSubmit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RepeatSubmit(interval = 10000)
    @PostMapping("/repeat/submit")
    public String repeatSubmit(@RequestBody User user) {
        return JSONUtil.toJsonStr(user);
    }

    @RateLimiter(time = 10, count = 2)
    @GetMapping("/rate/limit")
    public String hell2(String key) {
        return key;
    }
}