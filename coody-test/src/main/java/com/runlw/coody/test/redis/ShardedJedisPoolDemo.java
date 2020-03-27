package com.runlw.coody.test.redis;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SharedJedisPoolDemo
 * @Description (集群式)连接池操作redis
 * @Date 2019/10/23 8:28
 * @Created by 86150
 */
public class ShardedJedisPoolDemo {

    public static void main(String[] args) {
        // 构建连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(50);

        // 定义集群信息
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379));
        //shards.add(new JedisShardInfo("127.0.0.1", 6380));

        // 定义集群连接
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, shards);
        ShardedJedis shardedJedis = null;

        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();

            for (int i = 0; i < 100; i++) {
                shardedJedis.set("key" + i, "value" + i);
            }

            // 从redis中获取数据
            String value = shardedJedis.get("mytest");
            System.out.println(value);
        } catch (Exception e) {
            System.out.println("===exception===");
        } finally {
            if (shardedJedis != null) {
                // 关闭连接
                shardedJedis.close();
            }
        }

        // 关闭连接池
        shardedJedisPool.close();
    }
}
