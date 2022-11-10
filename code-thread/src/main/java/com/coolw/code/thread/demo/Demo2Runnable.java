package com.coolw.code.thread.demo;

import java.io.IOException;

/**
 * Runnable
 *
 * @author coolw
 * @date 2022/10/24 17:25
 * @since 1.0
 */
public class Demo2Runnable {
    
    public static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("thread name:" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new MyThread()).start();
    }
}
