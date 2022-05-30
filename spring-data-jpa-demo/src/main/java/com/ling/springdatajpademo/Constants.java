package com.ling.springdatajpademo;

/**
 * 常量定义
 *
 * @author liaoshuhao
 * @version 1.0
 * @date 2021/9/27 上午11:46
 */
public class Constants {

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int TEN = 10;
    public static final int THIRTY = 30;

    /***
     * RedisKey常量
     */
    public class RedisKey {
        /*** 国家法定节假日*/
        public static final String HOLIDAY_LEGAL = "HOLIDAY_LEGAL:";

    }

    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 菜单类型
     */
    public interface MenuType{
        /**
         * 框架
         */
        String FRAME = "frame";
        /**
         * 菜单
         */
        String MENU = "menu";
        /**
         * 按钮
         */
        String BUTTON = "button";

    }

}
