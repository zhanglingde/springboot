import com.ling.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountTest {

    // 加有事物的service
    @Autowired
    @Qualifier("accountServiceImpl_old")
    private IAccountService as;

    // 动态代理实现的service
    @Resource(name = "proxyAccountService")
    private IAccountService proxyAccountService;


    @Test
    public  void testTransfer(){
        // as.transfer("aaa","bbb",100f);
        proxyAccountService.transfer("aaa","bbb",100f);
    }


}
