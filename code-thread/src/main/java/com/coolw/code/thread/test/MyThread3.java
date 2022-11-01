package com.coolw.code.thread.test;

/**
 * @Classname MyThread2
 * @Description TODO
 * @Author lw
 * @Date 2020-03-25 13:46
 */
public class MyThread3 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        // 只声明线程，不调用start()方法，线程状态为NEW
        System.out.println(thread.getState());

        thread.start();
        // 调用start()方法启动线程，线程状态RUNNABLE
        System.out.println(thread.getState());

        MyCounter counter = new MyCounter();
        // 线程1调用同步方法，模拟阻塞
        new Thread(() -> counter.increase()).start();
        // 线程2继续调用同步阻塞方法
        Thread th = new Thread(()-> counter.increase());
        th.start();
        // 主线程等10毫秒
        Thread.sleep(10);
        // 此时线程2的状态为 BLOCKED
        System.out.println(th.getState());
    }

}
