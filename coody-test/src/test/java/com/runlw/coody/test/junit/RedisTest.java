package com.runlw.coody.test.junit;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisTest {

    private Jedis jedis;

    @Test
    public void setJedis() {
        //连接redis服务器(在这里是连接本地的)
        jedis = new Jedis("localhost", 6379);
        //权限认证
        jedis.auth("lw");
        System.out.println("连接服务成功");
    }

    @Test
    public void redis_1() {
        Jedis jedis = new Jedis("localhost", 6379);

        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start > 1000) {
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");
            }
        } finally {
            jedis.close();
        }

        System.out.println("redis每秒操作：" + i + "次");
    }

}
