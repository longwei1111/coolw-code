package com.coolw.code.test.thread.demo;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/25 13:30
 * @since 1.0
 */
public class Demo4Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NamedThreadFactory threadFactory = new NamedThreadFactory("task-", false);
        ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);
        Future<Integer> future = executorService.submit(new Demo4Task());
        System.out.println("result:" + future.get());
        executorService.shutdown();
    }
}
