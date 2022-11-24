package com.coolw.code.test.client;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier测试：让一组线程达到一种状态后再全部同时执行
 *
 * @author coolw
 * @date 2022/7/8 15:15
 * @since 1.0
 */
public class CyclicBarrierTest {

    private static int num = 3;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(num,  () -> {
        System.out.println("----------所有人已就位,开始开晨会----------");
    });
    private static ExecutorService executor = Executors.newFixedThreadPool(num);
    
    public static void main(String[] args) {
        executor.submit(() -> {
            System.out.println("A人员上厕所");
            try {
                Thread.sleep(4000);
                System.out.println("A人员已就位");
                cyclicBarrier.await();
                System.out.println("晨会结束,A人员离开");
            } catch (InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            System.out.println("B人员上厕所");
            try {
                Thread.sleep(2000);
                System.out.println("B人员已就位");
                cyclicBarrier.await();
                System.out.println("晨会结束,B人员离开");
            } catch (InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            System.out.println("C人员上厕所");
            try {
                Thread.sleep(3000);
                System.out.println("C人员已就位");
                cyclicBarrier.await();
                System.out.println("晨会结束,C人员离开");
            } catch (InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }
        });
        
        // 关闭线程池
        executor.shutdown();
    }
}
