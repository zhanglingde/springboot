import com.ling.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTemplateTest {


    /**
     * redisTemplate 测试字符串
     */
    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml","spring_redis.xml");
        UserService userService = (UserService) ac.getBean("userService");
        userService.getString("test");
    }
}
