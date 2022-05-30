package com.ling.droolsdemo.entry;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户级别
     */
    private int level;
}