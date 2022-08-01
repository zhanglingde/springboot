import com.ling.service.UserService;
import com.ling.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ListTemplateTest {

    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml","spring_redis.xml");
        UserService userService = ac.getBean("userService",UserService.class);

        // userService.listAdd();
        List<String> list =  userService.listRange(0,3);
        for (String s : list) {
            System.out.println(s);
        }

    }

    // ------------------ 任务队列-----------------------------------------------
    @Test
    public void test02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml","spring_redis.xml");
        UserServiceImpl userServiceImpl = ac.getBean("userService",UserServiceImpl.class);

        // 模拟物流下单
        String cardId = "1001";
        // 1. 用户支付完成后，生成对应的物流队列
        userServiceImpl.listQueueInit(cardId);

        System.out.println("-----当前待执行的任务队列：");
        List<String> waitList = userServiceImpl.listQueueWait(cardId);
        for (String s : waitList) {
            System.out.println(s);
        }

        System.out.println("-------物流开始运输---------");
        userServiceImpl.listQueueTouch(cardId);

        System.out.println("-----物流运输后，当前待执行的任务队列：");
        List<String> waitList2 = userServiceImpl.listQueueWait(cardId);
        for (String s : waitList2) {
            System.out.println(s);
        }


        System.out.println("-----已完成任务队列：");
        List<String> succList = userServiceImpl.listQueueSucc(cardId);
        for (String s : succList) {
            System.out.println(s);
        }

    }
}
