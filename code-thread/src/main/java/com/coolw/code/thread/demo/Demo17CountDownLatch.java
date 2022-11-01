package com.coolw.code.thread.demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:
 *      适用于多线程中,等待所有子线程执行完成后再继续执行的场景。屏障不能复用
 *      底层是基于实现AbstractQueuedSynchronizer的Sync同步器
 * 
 * demo背景:
 *      游戏开始前,需要先执行[加载地图模型][加载人物模型][加载背景音乐]步骤后进入游戏
 *      
 * @author coolw
 * @date 2022/11/9 10:26
 * @since 1.0
 */
public class Demo17CountDownLatch {

    static class PreTaskThread implements Runnable {
        private final String task;
        private final CountDownLatch countDownLatch;
        
        public PreTaskThread(String task, CountDownLatch countDownLatch) {
            this.task = task;
            this.countDownLatch = countDownLatch;
        }
        
        @Override
        public void run() {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(task + "-任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }
    
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        
        // 主任务线程
        new Thread(() -> {
            System.out.println("等待数据加载......");
            System.out.println(String.format("还有%d个前置任务", countDownLatch.getCount()));
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("数据加载完成,开始进入游戏!");
        }).start();

        // 前置任务线程
        new Thread(new PreTaskThread("加载地图数据", countDownLatch)).start();
        new Thread(new PreTaskThread("加载任务模型", countDownLatch)).start();
        new Thread(new PreTaskThread("加载背景音乐", countDownLatch)).start();
    }
}
