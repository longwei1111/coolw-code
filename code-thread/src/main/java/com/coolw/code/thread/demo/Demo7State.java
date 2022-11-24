package com.coolw.code.thread.demo;

import cn.hutool.core.util.BooleanUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * 线程状态Thread.State
 *      NEW
 *      RUNNABLE
 *      BLOCKED
 *      WAITING
 *      TIMED_WAITING
 *      TERMINATED
 *     
 * @author coolw
 * @date 2022/10/28 13:59
 * @since 1.0
 */
public class Demo7State {

    private final Object lock = new Object();
    
    @Test
    public void testNEW() {
        // 创建线程,状态为NEW
        Thread thread = new Thread(() -> System.out.println("--------"));
        System.out.println("state:" + thread.getState());
    }
    
    @Test
    public void testRUNNABLE() {
        Thread thread = new Thread(() -> System.out.println("--------"));
        // 调用start()方法,状态为RUNNABLE
        thread.start();
        System.out.println("state:" + thread.getState());
    }
    
    @Test
    public void testBLOCKED() throws InterruptedException {
        Thread t1 = new Thread(() -> testMethod(), "t1");
        Thread t2 = new Thread(() -> testMethod(), "t2");
        
        // t1线程先拿到锁
        t1.start();
        Thread.sleep(10L);
        // t2线程去获取锁，此时t1线程还未释放锁，固t2线程状态为BLOCKED
        t2.start();
        System.out.println("t1 state:" + t1.getState());
        System.out.println("t2 state:" + t2.getState());
    }
    
    @Test
    public void testWAITING() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("wait over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notifyAll();
            }
        }, "t2");

        t1.start();
        Thread.sleep(2000L);
        t2.start();
        System.out.println("t1 state:" + t1.getState());
        System.out.println("t2 state:" + t2.getState());
    }
    
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
    
    private  void testMethod() {
        synchronized(lock) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
