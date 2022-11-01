package com.coolw.code.thread.demo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier:
 *      与CountDownLatch功能相似。屏障可以复用(调用reset方法)
 *      底层是基于ReentrantLock和Condition实现的等待/通知模式(dowait方法)
 *
 * demo背景:
 *      游戏有3个关卡,每个关卡都有[加载地图模型][加载人物模型][加载背景音乐];
 *      如果使用CountDownLatch,由于屏障不能复用,固每个关卡需要创建一个CountDownLatch实例;
 *      此时可以用CyclicBarrier,只需要创建一个实例,当达到屏障后,可以调用reset方法进行重置,达到复用的目的
 *      
 * @author coolw
 * @date 2022/11/9 13:10
 * @since 1.0
 */
public class Demo18CyclicBarrier {

    static class PreTaskThread implements Runnable {
        private final String task;
        private final CyclicBarrier cyclicBarrier;
        
        public PreTaskThread(String task , CyclicBarrier cyclicBarrier) {
            this.task = task;
            this.cyclicBarrier = cyclicBarrier;
        }
        
        @Override
        public void run() {
            // 假设总共3个关卡
            for (int i = 1; i < 4; i++) {
                try {
                    Random random = new Random();
                    Thread.sleep(random.nextInt(1000));
                    System.out.printf("关卡%d的%s完成%n", i, task);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
            // 重置屏障
            cyclicBarrier.reset();
        }
    }
    
    public static void main(String[] args) {
        // 执行await方法后,如果执行的线程数量等于构造方法中的任务数量,就达到了屏障
        // CyclicBarrier构造方法可以传入一个runnable任务,当达到了屏障时会执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3
                , () -> System.out.println("本关卡所有前置任务完成,开始游戏......"));
        
        new Thread(new PreTaskThread("加载地图数据", cyclicBarrier)).start();
        new Thread(new PreTaskThread("加载人物模型", cyclicBarrier)).start();
        new Thread(new PreTaskThread("加载背景音乐", cyclicBarrier)).start();
    }
}
