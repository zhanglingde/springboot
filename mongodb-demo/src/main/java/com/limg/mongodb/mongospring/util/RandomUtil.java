package com.limg.mongodb.mongospring.util;


public class RandomUtil {
    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
}
