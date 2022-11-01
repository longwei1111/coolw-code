package com.coolw.code.test.thread;

/**
 * @Classname MyThread2
 * @Description 死锁
 * @Author lw
 * @Date 2020-03-25 13:46
 */
public class MyThread7 {

    static Object object1 = new Object();
    static Object object2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            synchronized (object1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });


        Thread thread2 = new Thread(() -> {
            synchronized (object2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
