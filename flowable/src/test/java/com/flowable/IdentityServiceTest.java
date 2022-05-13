package com.flowable;

import liquibase.pro.packaged.A;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zhangling
 * @date 2022/5/13 4:11 下午
 */
@SpringBootTest
public class IdentityServiceTest {

    @Autowired
    IdentityService identityService;

    @Test
    void test(){
        // 用户查询，用户 id 对应 xml 里配置的用户
        List<User> users = identityService.createUserQuery().list();
        // 分组查询，用户 id 对应 xml 里配置的分组，
        List<Group> groups = identityService.createGroupQuery().list();
    }
}
