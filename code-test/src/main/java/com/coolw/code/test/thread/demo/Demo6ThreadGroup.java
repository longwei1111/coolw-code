package com.coolw.code.test.thread.demo;

import org.junit.Test;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/26 8:42
 * @since 1.0
 */
public class Demo6ThreadGroup {

    @Test
    public void test1() {
        Thread testThread = new Thread(() -> {
            // 没有显示指定线程组名称时，默认会将父类线程名称做为子线程的线程组名称
            String threadGroupName = Thread.currentThread().getThreadGroup().getName();
            System.out.println("testThread所在线程组:" + threadGroupName);
            String threadName = Thread.currentThread().getName();
            System.out.println("testThread线程名称:" + threadName);
        });
        testThread.start();

        System.out.println("main方法线程名称:" + Thread.currentThread().getName());
    }

    /**
     * 线程组统一异常处理
     */
    @Test
    public void test2() {
        ThreadGroup group = new ThreadGroup("gorup1") {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程：" + t.getName() + ",执行异常：" + e.getMessage());
            }
        };
        
        Thread thread = new Thread(group, () -> {
                throw new RuntimeException("测试异常");
        });
        thread.start();
    }
    
}
