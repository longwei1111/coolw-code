package com.coolw.code.thread.file.client;

import redis.clients.jedis.Jedis;

/**
 * @Classname RedisDemo
 * @Description redis示例
 * @Date 2019/10/18 8:36
 * @Created by lw
 */
public class RedisDemo {

    public static void main(String[] args) {
        // 构建jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 向redis中添加一个String类型的字符串，key为mytest，value为123
        jedis.set("mytest", "123");
        // 从redis中读取数据
        String value = jedis.get("mytest");
        System.out.println(value);

        // 关闭连接
        jedis.close();
    }

}
