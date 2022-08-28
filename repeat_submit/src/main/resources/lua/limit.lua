-- 脚本写在服务器上，生成一个闪电函数，调用时将这个函数值传过去，服务器根据这个值判断调用哪个脚本
-- key 是一个集合（下标从 1 开始）
local key = KEYS[1]
-- 参数也是一个集合，tonumber 参数转换成数字
local time = tonumber(ARGV[1])
local count = tonumber(ARGV[2])
-- 限流 key，获取 value，value 存储调用的次数
local current = redis.call('get', key)
-- current 存在，且超过限流次数
if current and tonumber(current) > count then
    return tonumber(current)
end
current = redis.call('incr', key)
-- 等于 1 表示没有其他线程操作过，设置过期时间；如果并发其他线程执行过，就不再设置过期时间
if tonumber(current) == 1 then
    redis.call('expire', key, time)
end
return tonumber(current)