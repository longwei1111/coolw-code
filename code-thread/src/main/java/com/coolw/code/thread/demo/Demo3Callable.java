package com.coolw.code.thread.demo;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * Callable
 *
 * @author coolw
 * @date 2022/10/25 13:30
 * @since 1.0
 */
public class Demo3Callable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("thread name:" + Thread.currentThread().getName());
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NamedThreadFactory threadFactory = new NamedThreadFactory("coolw-", false);
        ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);
        Future<Integer> future = executorService.submit(new Demo3Callable());
        System.out.println("result:" + future.get());
        executorService.shutdown();
    }
}
