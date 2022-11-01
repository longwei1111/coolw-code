package com.coolw.code.thread.test;

/**
 * @Classname MyThread
 * @Description TODO
 * @Author lw
 * @Date 2020-03-25 10:13
 */
public class MyThread implements Runnable {
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
