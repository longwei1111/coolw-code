package com.coolw.code.thread.test;

/**
 * @Classname SynchronizedTest
 * @Description TODO
 * @Author lw
 * @Date 2020-03-25 13:58
 */
public class SynchronizedTest extends Thread {

    int number = 10;

    public synchronized void first() {
        System.out.println("this is first!");
        number += 1;
    }

    public synchronized void second() throws InterruptedException {
        System.out.println("this is second!");
        // 不会释放锁，阻塞当前线程，即放弃CPU
        Thread.sleep(1000);
        // 释放锁，不阻塞当前线程
        //this.wait(1000);
        number = number * 100;
    }


    @Override
    public void run() {
        first();
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        test.start();
        test.second();

        Thread.sleep(10);
        System.out.println(test.number);
    }
}
