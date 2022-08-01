package com.ling.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;
    private String name;
    private String username;
    private String password;
    private Integer age;

    public static String getKeyName(){
        return "user:";
    }
}
