package ling.dao.impl;

import ling.dao.IAccountDao;

/**
 * 账户的持久层实现类
 */
public class AccountDaoImpl implements IAccountDao {

    public void saveAccount() {

        System.out.println("保存了账户");
    }
}
