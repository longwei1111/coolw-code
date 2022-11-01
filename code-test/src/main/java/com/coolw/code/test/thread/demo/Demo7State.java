package com.coolw.code.test.thread.demo;

import org.junit.Test;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/28 13:59
 * @since 1.0
 */
public class Demo7State {
    
    @Test
    public void test1() {
        Thread t1 = new Thread(() -> testMethod(), "t1");
        Thread t2 = new Thread(() -> testMethod(), "t2");
        
        t1.start();
        //Thread.sleep(1000L);
        //t1.join();
        //t1.join(1000L);
        t2.start();

        System.out.println(t1.getName() + ":" + t1.getState());
        System.out.println(t2.getName() + ":" + t2.getState());
    }
    
    private synchronized void testMethod() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
