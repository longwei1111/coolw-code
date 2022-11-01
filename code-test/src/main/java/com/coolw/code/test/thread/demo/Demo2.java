package com.coolw.code.test.thread.demo;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/24 17:25
 * @since 1.0
 */
public class Demo2 {
    
    public static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("thread name:" + Thread.currentThread().getName());

        }
    }

    public static void main(String[] args) {
        // new Thread(new MyThread()).start();
        
        new Thread(() -> System.out.println(".................")).start();
    }
}
