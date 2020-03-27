package com.runlw.coody.test.thread;

/**
 * @Classname MyThread2
 * @Description TODO
 * @Author lw
 * @Date 2020-03-25 13:46
 */
public class MyThread5 extends Thread {

    @Override
    public void run() {
        synchronized (MyThread5.class){
            try {
                MyThread5.class.wait(1000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread5 thread5 = new MyThread5();
        thread5.start();

        // 主线程挂起200毫秒，等待thread5执行完成
        Thread.sleep(200);

        // 此时thread5处于一直被挂起，线程状态为TIMED_WAITING
        System.out.println(thread5.getState());
    }
}
