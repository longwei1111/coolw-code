package com.coolw.code.io.test;

import redis.clients.jedis.Jedis;

/**
 * @Classname Test
 * @Description TODO
 * @Author lw
 * @Date 2020-03-27 14:22
 */
public class Test {

    /**
     * 性能测试
     *
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        int i = 0;

        try{
            long startTime = System.currentTimeMillis();
            while(true){
                long endTime = System.currentTimeMillis();
                if(endTime - startTime > 1000){
                    // 已执行1秒
                    break;
                }

                i++;

                jedis.set("test" + i, String.valueOf(i));
            }
        }finally {
            jedis.close();
        }
        System.out.println("redis每秒执行：" + i + "次");
    }
}
