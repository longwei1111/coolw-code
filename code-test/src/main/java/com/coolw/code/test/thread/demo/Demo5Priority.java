package com.coolw.code.test.thread.demo;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/26 8:51
 * @since 1.0
 */
public class Demo5Priority {
    
    public class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.printf("当前执行的线程：%s，优先级：%s%n"
                    , Thread.currentThread().getName()
                    , Thread.currentThread().getPriority());
        }
    }

    /**
     * 设置线程优先级，默认线程优先级是5
     */
    @Test
    public void test1() {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1线程优先级:" + Thread.currentThread().getPriority());
        });
        thread1.start();
        
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2线程优先级:" + Thread.currentThread().getPriority());
        });
        thread2.setPriority(10);
        thread2.start();
    }

    /**
     * 线程优先级执行顺序
     *    业务系统中不能按照设置线程优先级来指定线程执行顺序
     *    java中的线程优先级不是特别的可靠，只是给操作系统的一个建议，操作系统不一定会采纳
     *    线程执行的顺序，是由操作系统的线程调度算法来决定的
     */
    @Test
    public void test2() {
        IntStream.range(1, 10).forEach(i -> {
            MyThread mt = new MyThread();
            mt.setPriority(i);
            mt.start();
        });
    }

    /**
     * 线程组和线程设置优先级
     *   当某个线程优先级大于所在的线程组的最大优先级时，那么该线程优先级会失效，取而代之的是线程组的优先级
     */
    @Test
    public void test3() {
        ThreadGroup group = new ThreadGroup("tg");
        group.setMaxPriority(5);
        
        Thread thread = new Thread(group, "t1");
        thread.setPriority(8);

        System.out.println("线程组优先级：" + group.getMaxPriority());
        System.out.println("线程优先级：" + thread.getPriority());
    }
}
