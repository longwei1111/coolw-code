package com.coolw.code.test.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量,计数器是递增的
 *
 * @author coolw
 * @date 2022/7/8 17:38
 * @since 1.0
 */
public class SemaphoreTest {

    private static int num = 3;
    private static int initNum = 0;
    
    private static Semaphore semaphore = new Semaphore(initNum);
    private static ExecutorService executor = Executors.newFixedThreadPool(num);
    
    public static void main(String[] args) throws InterruptedException {
        executor.submit(() -> {
            System.out.println("A员工在上厕所");
            try {
                Thread.sleep(4000);
                semaphore.release();
                System.out.println("A已就位");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            System.out.println("B员工在上厕所");
            try {
                Thread.sleep(2000);
                semaphore.release();
                System.out.println("B已就位");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            System.out.println("C员工在上厕所");
            try {
                Thread.sleep(3000);
                semaphore.release();
                System.out.println("C已就位");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        System.out.println("等待所有人就位");
        semaphore.acquire(num);
        System.out.println("所有人已就位,开始开晨会");
        
        // 关闭线程池
        executor.shutdown();
    }
}
