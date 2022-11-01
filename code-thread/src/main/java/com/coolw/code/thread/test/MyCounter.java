package com.coolw.code.thread.test;

/**
 * @Classname MyCounter
 * @Description TODO
 * @Author lw
 * @Date 2020-03-26 08:45
 */
public class MyCounter {

    static int counter = 0;

    public synchronized void increase() {
        counter++;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
