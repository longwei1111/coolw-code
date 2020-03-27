package com.runlw.coody.test.thread;

/**
 * @Classname MyThread2
 * @Description TODO
 * @Author lw
 * @Date 2020-03-25 13:46
 */
public class MyThread4 extends Thread {

    @Override
    public void run() {
        synchronized (MyThread4.class){
            try {
                MyThread4.class.wait();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread4 thread4 = new MyThread4();
        thread4.start();

        // 主线程挂起200毫秒，等待thread4执行完成
        Thread.sleep(200);

        // 此时thread4处于一直被挂起，线程状态为WAITING
        System.out.println(thread4.getState());
    }
}
