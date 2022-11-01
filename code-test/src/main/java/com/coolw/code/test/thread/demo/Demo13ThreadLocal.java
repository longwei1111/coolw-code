package com.coolw.code.test.thread.demo;

/**
 * ThreadLocal:每个线程创建自己的副本，每个线程可以访问自己内部的副本变量，线程之间互不影响
 *
 * @author coolw
 * @date 2022/10/31 13:28
 * @since 1.0
 */
public class Demo13ThreadLocal {
    
    static class ThreadA implements Runnable {
        
        private final ThreadLocal<String> threadLocal;
        
        public ThreadA(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal; 
        }
        
        @Override
        public void run() {
            threadLocal.set("A");
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA输出：" + threadLocal.get());
        }
    }

    static class ThreadB implements Runnable {

        private final ThreadLocal<String> threadLocal;

        public ThreadB(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("B");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadB输出：" + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        
        new Thread(new ThreadA(threadLocal)).start();
        new Thread(new ThreadB(threadLocal)).start();
    }
    
}
