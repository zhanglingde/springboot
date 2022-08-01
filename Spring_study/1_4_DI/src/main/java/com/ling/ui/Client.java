package com.ling.ui;

import com.ling.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     * 获取Spring的Ioc核心容器,并根据id获取对象
     * @param args
     */
    public static void main(String[] args) {
        // 1.获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.根据id获取bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService3");

        as.saveAccount();






    }
}
