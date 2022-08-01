package com.ling.test;

import com.ling.domain.User;

public class UserTest {

    private static User u2;

    public static void main(String[] args) {
        User user = new User(); //.var
        u2 = new User();  //.filed 全局变量
        User u3 = new User(); // User.new
        User u4 = (User) new Object(); //.cast
        User u5 = (User) new Object(); // .castvar

        if (u5 == null) { // u5.null

        }

        if (u5 != null) { // u5.notnull

        }
        if (u5 != null) { // u5.nn

        }
        boolean flag = true;
        if (flag) { // u5.flag

        }
        while (flag) { // u5.while

        }

        // 循环
        String[] users = new String[5];
        for (int i = 0; i < users.length; i++) { // user.fori

        }
        for (String s : users) { // user.for

        }
        for (int i = users.length - 1; i >= 0; i--) { // user.forr

        }




    }
}
