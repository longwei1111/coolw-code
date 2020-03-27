package com.runlw.coody.test.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Classname JedisPoolDemo
 * @Description (普通)连接池操作redis
 * @Date 2019/10/23 8:19
 * @Created by 86150
 */
public class JedisPoolDemo {

    public static void main(String[] args) {
        // 构建连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(50);
        // 构建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
        // 从连接池中获取连接
        Jedis jedis = jedisPool.getResource();
        // 读取数据
        String value = jedis.get("mytest");
        System.out.println(value);

        // 释放连接
        jedis.close();
    }

}
