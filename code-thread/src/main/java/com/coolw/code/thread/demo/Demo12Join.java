package com.coolw.code.thread.demo;

/**
 * join：是Thread类中的一个实例方法
 *   它的作用是让当前线程处于"等待"状态，等调用join方法的线程执行完后，再继续执行当前线程
 *   底层是调用的Object的wait方法
 *
 * @author coolw
 * @date 2022/10/31 10:56
 * @since 1.0
 */
public class Demo12Join {

    public static class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("线程：" + Thread.currentThread().getName() + "睡眠开始");
                Thread.sleep(2000L);
                System.out.println("线程：" + Thread.currentThread().getName() + "睡眠结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread(), "demo");
        thread.start();
        // 调用join方法后，当前线程main会进入"等待"状态，待调用join方法的线程执行完成后，再继续执行线程main
        thread.join();
        // 子线程睡眠2S，join方法等待1S，所以1S后，当前线程main不再等待子线程是否执行完成，当前线程会继续执行
        //thread.join(1000L)
        System.out.println("线程：" + Thread.currentThread().getName() + "执行结束");
    }
    
}
