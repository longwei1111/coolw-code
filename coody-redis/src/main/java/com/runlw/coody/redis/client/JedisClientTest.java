package com.runlw.coody.redis.client;

import com.runlw.coody.redis.pojo.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Classname JedisClient
 * @Description TODO
 * @Author lw
 * @Date 2020-03-27 10:51
 */
public class JedisClientTest {

    private Jedis jedis;
    private RedisTemplate redisTemplate;


    @Before
    public void setUp() {
        jedis = new Jedis("127.0.0.1", 6379);

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        redisTemplate = context.getBean(RedisTemplate.class);
    }

    /**
     * jedis 操作 String
     * CRUD
     */
    @Test
    public void testString() {
        /* 添加数据 */
        jedis.set("runlw", "12346");
        System.out.println(jedis.get("runlw"));

        /* 修改数据 */
        // 1. 在原基础上修改
        jedis.append("runlw", "666666");
        System.out.println(jedis.get("runlw"));
        // 2. 直接覆盖原来的数据
        jedis.set("runlw", "888888");
        System.out.println(jedis.get("runlw"));

        /* 删除key对应的记录 */
        jedis.del("runlw");
        System.out.println(jedis.get("runlw"));

        /*
         * mset相当于
         * jedis.set("name", "zhangsan");
         * jedis.set("password", "123456");
         */
        jedis.mset("name", "zhangsan", "password", "123456");
        System.out.println(jedis.mget("name", "password"));
    }

    /**
     * jedis 操作 Map
     */
    @Test
    public void testMap() {
        // 添加数据
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", "zhangsan");
        userMap.put("pass", "123456");
        jedis.hmset("user", userMap);
        System.out.println(jedis.hmget("user", "name"));

        // 删除map中某个j键值
        jedis.hdel("user", "pass");
        // 获取数据
        System.out.println(jedis.hmget("user", "pass"));
        System.out.println(jedis.hlen("user"));
        System.out.println(jedis.exists("user"));
        System.out.println(jedis.hkeys("user"));
        System.out.println(jedis.hvals("user"));

        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(jedis.hget("user", key));
        }
    }

    /**
     * jedis 操作 List
     */
    @Test
    public void testList() {
        // 添加
        jedis.lpush("runlw", "1111");
        jedis.lpush("runlw", "2222");
        jedis.lpush("runlw", "3333");
        jedis.rpush("runlw", "4444");
        // 输出
        System.out.println(jedis.lrange("runlw", 0, 1));
    }

    /**
     * jedis 操作 Set
     */
    @Test
    public void testSet() {
        // 添加
        jedis.sadd("sname", "zhangsan");
        jedis.sadd("sname", "lisi");
        jedis.sadd("sname", "wangwu");
        // 移除wangwu
        jedis.srem("sname", "wangwu");
        // 获取所有的value
        System.out.println(jedis.smembers("sname"));
        // 判断lisi是否在sname集合中
        System.out.println(jedis.sismember("sname", "lisi"));
        // 集合的元素个数
        System.out.println(jedis.scard("sname"));
    }

    @Test
    public void test() throws InterruptedException {
        // 返回所有的key
        System.out.println(jedis.keys("*"));
        // 使用通配符返回key
        System.out.println(jedis.keys("*name"));
        // 删除key
        System.out.println(jedis.del("sname"));

        // 返回key的有效时间,-1代表永久
        System.out.println(jedis.ttl("runlw"));
        // 指定key的有效时间
        jedis.setex("timekey", 10, "min");
        // 主线程睡眠5秒，阻塞
        Thread.sleep(5000);
        System.out.println(jedis.ttl("timekey"));

        // 排序
        jedis.rpush("user_big", "1");
        jedis.lpush("user_big", "7");
        jedis.lpush("user_big", "6");
        jedis.lpush("user_big", "9");
        System.out.println(jedis.lrange("user_big", 0, -1));
        System.out.println(jedis.sort("user_big"));
        System.out.println(jedis.lrange("user_big", 0, -1));
    }

    /**
     * redis 读写对象，读写不一定在同一个 redis 连接中操作
     */
    @Test
    public void test_save_object() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("jonn");
        role.setNote("suozhang");

        redisTemplate.opsForValue().set("role", role);

        Role res = (Role) redisTemplate.opsForValue().get("role");
        System.out.println(res);
    }

    /**
     * redis 读写对象,且读写在同一个 redis 连接中操作
     */
    @Test
    public void test_save_object_1() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("jonn");
        role.setNote("suozhang");

        SessionCallback sessionCallback = new SessionCallback<Role>() {
            @Override
            public Role execute(RedisOperations ops) throws DataAccessException {
                ops.boundValueOps("role_1").set(role);
                return (Role) ops.boundValueOps("role_1").get();
            }
        };

        Role res = (Role) redisTemplate.execute(sessionCallback);
        System.out.println(res);
    }
}
