package com.coolw.code.test.client;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS测试
 *
 * @author coolw
 * @date 2022/7/7 22:28
 * @since 1.0
 */
public class AtomicTest {

    //private static int count = 0;
    private static final AtomicInteger count = new AtomicInteger(0);
    
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j++) {
                        /*synchronized (AtomicTest.class) {
                            count++;
                        }*/
                        count.incrementAndGet();
                    }
                }
            }).start(); 
        }
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
    }
    
}
