import com.ling.config.SpringConfiguration;
import com.ling.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void testFindAll() {
        // // 获得ioc容器对象
        // ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // // 获得service对象
        // IAccountService as = ac.getBean("accountService", IAccountService.class);

        accountService.transfer("aaa","bbb",100f);


    }

}
