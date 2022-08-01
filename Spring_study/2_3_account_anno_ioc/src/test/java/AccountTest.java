import com.ling.domain.Account;
import com.ling.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void testFindAll() {
        // 获得ioc容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 获得service对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);

        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }

    }

    @Test
    public void testFindOne() {
        Account account = accountService.findAccountById(3);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("张三");
        account.setMoney(1234f);
        // 获得ioc容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 获得service对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);

        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        // 获得ioc容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 获得service对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
    }

    @Test
    public void testDelete() {
        // 获得ioc容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 获得service对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
    }
}
