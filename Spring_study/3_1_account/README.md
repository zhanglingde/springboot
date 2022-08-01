# 在操作中添加了事物
1. service中的transfer()方法存在事物问题

问题：

在transfer()方法中，dbutils.QueryRunner每次执行一次数据库操作都会创建一个新的对象，
并且每次都会获得一次连接

解决：

应该在整个方法中，只创建一个connection连接，需要使用ThreadLocal对象把Connection和当前线程绑定，从而
使一个线程中只有一个能控制事物的对象

1. 通过ConnectionUtils工具类保证当前线程只有一个连接
2. 通过TransactionManager工具类把连接和事物绑定在一起
```markdown
连接池、线程池的作用是在初始化的过程中，就把需要用到在一些连接创建好，放入连接
池中
从而在使用的过程中，直接从连接池中拿取，不再需要消耗建立连接的时间，使用完毕后
释放连接也不是关闭连接，而是把连接放回连接池中

# 问题
而我们的问题是把连接关闭了，连接和线程的绑定还在，但是下次获取线程时，连接已经不可用了，
所以在关闭连接的时候，还需要把连接和线程解绑
ConnectionUtil的removeConnection方法
```
3 AccountServiceImpl_OLD中的业务层方法为手动添加事物处理的结果

# 动态代理

BeanFactory,AccountService使用动态代理实现事物
