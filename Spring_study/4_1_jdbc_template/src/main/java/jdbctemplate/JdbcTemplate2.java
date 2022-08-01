package jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplate2 {
    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //1.创建JdbcTemplate对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);

        //2.执行操作
        jt.execute("insert into account(name,money)values('ccc',1000)");
    }


}
