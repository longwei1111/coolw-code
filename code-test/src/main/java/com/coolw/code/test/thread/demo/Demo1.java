package com.coolw.code.test.thread.demo;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/24 17:24
 * @since 1.0
 */
public class Demo1 {
    
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("thread name:" + currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.start();
        // 多次调用同一个线程的start()方法会报错
        //mt.start();
    }
    
}
