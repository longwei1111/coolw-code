package com.coolw.code.test.thread.demo;

import org.junit.Test;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/28 16:15
 * @since 1.0
 */
public class Demo11Volatile {
    
    /** volatitle保证了内存的可见性，在多个线程中，可以获取最新的值；也可以防止重排序(禁止jvm优化执行顺序) */
    private volatile int count = 1;
    
    public class ThreadA implements Runnable {
        @Override
        public void run() {
            while (count < 11) {
                if (count % 2 == 1) {
                    System.out.println("ThreadA count:" + count);
                    synchronized (this) {
                        count++;
                    }
                }
            }
        }
    }
    
    public class ThreadB implements Runnable {
        @Override
        public void run() {
            while (count < 11) {
                if (count % 2 == 0) {
                    System.out.println("ThreadB count:" + count);
                    synchronized (this) {
                        count++;
                    }
                }
            }
        }
    }

    /**
     * ThreadA，ThreadB线程依次1，2，3，4，5，6，7，8，9，10
     */
    @Test
    public void test() throws InterruptedException {
        Thread a = new Thread(new ThreadA());
        Thread b = new Thread(new ThreadB());
        
        a.start();
        Thread.sleep(100L);
        b.start();
        
        Thread.sleep(3000L);
    }
    
}
