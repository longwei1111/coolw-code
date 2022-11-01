package com.coolw.code.thread.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/11/9 16:21
 * @since 1.0
 */
public class Demo19Stream {

    /**
     * 串行执行(单线程)
     * 
     * main: 1 + 2 = 3
     * main: 3 + 3 = 6
     * main: 6 + 4 = 10
     * main: 10 + 5 = 15
     * main: 15 + 6 = 21
     * main: 21 + 7 = 28
     * main: 28 + 8 = 36
     * main: 36 + 9 = 45
     * 45
     */
    @Test
    public void testStream() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce((a, b) -> {
                    System.out.printf("%s: %d + %d = %d%n", Thread.currentThread().getName(), a, b, a + b);
                    return a + b;
                })
                .ifPresent(System.out::println);
    }

    /**
     *  parallel():并行执行(多线程),利用多核处理器提高计算效率,底层使用的Fork/Join框架
     *             使用了ForkJoinPool里面的commonPool里面的worker线程
     * 
     * ForkJoinPool.commonPool-worker-3: 1 + 2 = 3
     * ForkJoinPool.commonPool-worker-4: 8 + 9 = 17
     * ForkJoinPool.commonPool-worker-4: 7 + 17 = 24
     * ForkJoinPool.commonPool-worker-1: 3 + 4 = 7
     * ForkJoinPool.commonPool-worker-2: 5 + 6 = 11
     * ForkJoinPool.commonPool-worker-1: 3 + 7 = 10
     * ForkJoinPool.commonPool-worker-2: 11 + 24 = 35
     * ForkJoinPool.commonPool-worker-2: 10 + 35 = 45
     * 45
     */
    @Test
    public void testStreamParallel() throws InterruptedException {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallel()
                .reduce((a, b) -> {
                    System.out.printf("%s: %d + %d = %d%n", Thread.currentThread().getName(), a, b, a + b);
                    return a + b;
                })
                .ifPresent(System.out::println);
    }

    /**
     * 单线程和多线程性能对比:计算10000000个1~100的随机数字之和
     */
    @Test
    public void testParallel() {
        System.out.println("计算机的核数:" + Runtime.getRuntime().availableProcessors());
        int max = 10000000;
        Random random = new Random();
        List<Integer> list = new ArrayList<>(max);
        for (int i = 0;i < max;i++) {
            list.add(random.nextInt(100));
        }
        
        long startTime = getCurrentTime();
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);
        System.out.println("单线程计算耗时:" + (getCurrentTime() - startTime));
        
        // 多线程中,线程的创建、销毁和线程上下文切换存在一定的开销。固不能用:单线程处理时间除以计算机核数
        startTime = getCurrentTime();
        list.stream().parallel().reduce(Integer::sum).ifPresent(System.out::println);
        System.out.println("多线程计算耗时:" + (getCurrentTime() - startTime));
    }
    
    private long getCurrentTime() {
        return System.currentTimeMillis();
    }
            
}
