package com.runlw.coody.test.thread;

/**
 * @Classname MyThread1
 * @Description TODO
 * @Author lw
 * @Date 2020-03-25 13:39
 */
public class MyThread1 extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
