package com.ling.service.impl;

import com.ling.dao.IAccountDao;
import com.ling.service.IAccountService;


/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {


    private IAccountDao accountDao ;

    public AccountServiceImpl(){
        System.out.println("对象创建了");
    }

    public void  saveAccount(){
        accountDao.saveAccount();
    }
}
