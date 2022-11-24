package com.coolw.code.thread.demo;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * 有锁
 *
 * @author coolw
 * @date 2022/10/28 15:02
 * @since 1.0
 */
public class Demo9Lock {

    private final Object lock = new Object();
    
    public class ThreadC implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                IntStream.range(1, 50).forEach(i -> System.out.println("ThreadC:" + i));
            }
        }
    }

    public class ThreadD implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                IntStream.range(1, 50).forEach(i -> System.out.println("ThreadD:" + i));
            }
        }
    }

    @Test
    public void test2() throws InterruptedException {
        Thread t3 = new Thread(new ThreadC());
        Thread t4 = new Thread(new ThreadD());
        
        t3.start();
        // 等待10ms，先让t3线程拿到锁（防止t4线程先拿到锁）
        Thread.sleep(10L);
        t4.start();
        Thread.sleep(2000L);
    }

}
