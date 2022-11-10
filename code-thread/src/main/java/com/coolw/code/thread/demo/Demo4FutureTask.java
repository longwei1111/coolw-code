package com.coolw.code.thread.demo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * FutureTask
 *
 * @author coolw
 * @date 2022/11/10 20:17
 * @since 1.0
 */
public class Demo4FutureTask {

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("thread name:" + Thread.currentThread().getName());
            return 1;
        }
    }
    
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        executorService.submit(futureTask);
        System.out.println("result:" + futureTask.get());
        executorService.shutdown();
    }
}
