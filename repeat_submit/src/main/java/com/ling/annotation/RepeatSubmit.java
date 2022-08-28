package com.ling.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RepeatSubmit {
    /**
     * 两个请求之间的间隔时间
     * @return
     */
    int interval() default 5000;

    /**
     * 重复提交时候的提示文本
     * @return
     */
    String message() default "不允许重复提交，请稍后再试";
}