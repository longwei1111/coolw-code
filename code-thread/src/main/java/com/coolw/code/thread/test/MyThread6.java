package com.coolw.code.thread.test;

/**
 * @Classname MyThread2
 * @Description TODO
 * @Author lw
 * @Date 2020-03-25 13:46
 */
public class MyThread6 {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread.start();

        // 让主线程等待10毫秒
        Thread.sleep(10);
        // 此时线程状态为完成状态TERMINATED
        System.out.println(thread.getState());
    }
}
