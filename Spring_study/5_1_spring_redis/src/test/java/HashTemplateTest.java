import com.ling.bean.User;
import com.ling.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HashTemplateTest {

    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml","spring_redis.xml");
        UserService userService = ac.getBean("userService",UserService.class);
        User user = new User();
        user.setId(1);
        user.setName("zhangling");

        userService.add(user);

    }

    /**
     * 查询hash对象
     */
    @Test
    public void test02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml","spring_redis.xml");
        UserService userService = ac.getBean("userService",UserService.class);
        userService.selectById(5);
    }
}
