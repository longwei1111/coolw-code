package com.coolw.code.test.thread.demo;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/28 15:35
 * @since 1.0
 */
public class Demo10WaitNotifyDemo {

    private final Object lock = new Object();

    private class ThreadE implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                IntStream.range(1, 10).forEach(i -> {
                    System.out.println("ThreadE:" + i);
                    try {
                        // 唤醒持有lock的线程，这个时候当前线程还没有释放锁,会继续执行
                        lock.notify();
                        // 当前线程释放锁，其他持有lock线程执行
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                lock.notify();
            }
        }
    }

    private class ThreadF implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                IntStream.range(1, 10).forEach(i -> {
                    System.out.println("ThreadF:" + i);
                    try {
                        // 唤醒持有lock的线程，这个时候当前线程还没有释放锁,会继续执行
                        lock.notify();
                        // 当前线程释放锁，其他持有lock线程执行
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                lock.notify();
            }
        }
    }

    /**
     * E,F持有同一把锁lock，依次打印
     */
    @Test
    public void test1() throws InterruptedException {
        new Thread(new ThreadE()).start();
        // 先让E线程获取到锁，先执行
        Thread.sleep(10L);
        new Thread(new ThreadF()).start();

        Thread.sleep(5000L);
    }
}
