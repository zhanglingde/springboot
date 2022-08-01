# 纯注解配置ioc
1. 使用注解读取Spring配置，获取Springioc容器

- @Configuration注解

指定某个类是一个配置类，
ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

- @ComponentScan:

通过该注解指定spring在创建容器时要扫描的包，
    
- @Bean:

 将当前方法的返回值注入Spring容器中
   
- @Import

 用于到入其他类，到用了@Import时，当前类就是父配置类，其他导入的类是子配置类，此时不用使用@Configuration注解和@ComponentScan注解
 
- @PropertySource

放在类上，指定properties文件的位置  

   

