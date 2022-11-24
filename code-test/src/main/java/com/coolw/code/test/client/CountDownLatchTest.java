package com.coolw.code.test.client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatct测试
 *
 * @author coolw
 * @date 2022/7/8 14:18
 * @since 1.0
 */
public class CountDownLatchTest {

    private static int num = 3;
    private static CountDownLatch countDownLatch = new CountDownLatch(num);
    private static ExecutorService executor = Executors.newFixedThreadPool(num);
    
    public static void main(String[] args) throws InterruptedException {
        executor.submit(() -> {
            System.out.println("A人员上厕所");         
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("A人员已就位");
            }
        });
        
        executor.submit(() -> {
            System.out.println("B人员上厕所");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("B人员已就位");
            }
        });

        executor.submit(() -> {
            System.out.println("C人员上厕所");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("C人员已就位");
            }
        });

        System.out.println("等待上测试的人回来");
        // 阻塞等待所有线程执行完成
        countDownLatch.await();
        System.out.println("D人员已就位");
        System.out.println("全部人到齐，可以开会了");

        executor.shutdown();
    }
    
}
