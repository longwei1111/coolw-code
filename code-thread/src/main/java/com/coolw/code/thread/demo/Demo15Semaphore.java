package com.coolw.code.thread.demo;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量：一般用于资源有限的场景，去限制线程的数量
 *   1.acquire用来申请一个许可证permit
 *   2.release用来释放一个许可证permit
 *   3.当许可证减少到0时,再有线程来acquire permit,会阻塞该线程,直到有线程释放许可证为止
 *
 * @author coolw
 * @date 2022/11/8 16:36
 * @since 1.0
 */
public class Demo15Semaphore {
    
    public static void main(String[] args) {
        // 信号量初始化3个许可证
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new MyThread("NAME" + i, semaphore)).start();
        }
    }
    
    static class MyThread implements Runnable {
        
        private final String name;
        private final Semaphore semaphore;
        
        public MyThread(String name, Semaphore semaphore) {
            this.name = name;
            this.semaphore = semaphore;
        }
        
        @Override
        public void run() {
            try {
                // 获取许可证
                semaphore.acquire();
                System.out.printf("当前名称[%s]已获取许可证,还剩余[%d]个资源,还有[%d]个线程在等待%n"
                        , name, semaphore.availablePermits(), semaphore.getQueueLength());
                // 睡眠随机时间,打乱信号量释放顺序
                Random random = new Random();
                Thread.sleep(random.nextInt(1000));
                // 释放许可证
                semaphore.release();
                System.out.printf("当前名称[%s]已释放许可证%n", name);
            } catch (InterruptedException e){
                e.printStackTrace();       
            }
        }
    }
}
