package com.coolw.code.test.thread.demo;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/28 15:02
 * @since 1.0
 */
public class Demo8NoneLock {

    public class ThreadA implements Runnable {
        @Override
        public void run() {
            IntStream.range(1, 50).forEach(i -> System.out.println("ThreadA:" + i));
        }
    }

    public class ThreadB implements Runnable {
        @Override
        public void run() {
            IntStream.range(1, 50).forEach(i -> System.out.println("ThreadB:" + i));
        }
    }
    
    /**
     * 无锁
     */
    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread(new ThreadA());
        Thread t2 = new Thread(new ThreadB());
        
        t1.start();
        t2.start();
        Thread.sleep(2000L);
    }

}
