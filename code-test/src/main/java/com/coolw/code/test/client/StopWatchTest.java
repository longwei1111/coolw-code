package com.coolw.code.test.client;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 计时秒表
 *  1.StopWatch是spring-core包中的工具类，它是一个秒表工具，可以计时指定代码块的运行时间，做性能分析等等
 *  2.可以隐藏System.currentTimeMillis()，提高代码的可读性，减少计算错误的可能性
 *  3.StopWatch不保证线程安全
 *
 * @author coolw
 * @date 2022/8/24 9:38
 * @since 1.0
 */
public class StopWatchTest {

    /**
     * api:
     *  StopWatch():构造函数，构建一个新的秒表，不开始任何任务
     *  StopWatch(String id):构造函数，构建一个新的秒表，同时指定一个任务ID，不开始任何任务
     *  String getId():获取秒表的ID
     *  void start(String taskName):指定一个任务名称，启动当前任务
     *  void stop():停止当前任务
     *  boolean isRunning():当前表秒是否处于运行中
     *  String currentTaskName():获取当前正在运行中的任务名称
     *  long getLastTaskTimeMillis():获取上一个任务花费时间，单位秒
     *  String getLastTaskName():获取上一个任务名称
     *  TaskInfo getLastTaskInfo():获取上一个任务信息，TaskInfo为StopWatch的内部静态类（包含任务名称，任务花费时间）
     *  long getTotalTimeNanos():获取所有任务花费时间，单位纳秒
     *  long getTotalTimeMillis():获取所有任务花费时间，单位毫秒
     *  double getTotalTimeSeconds():获取所有任务花费时间，单位秒
     *  int getTaskCount():获取任务数量
     *  TaskInfo[] getTaskInfo():获取所有任务信息
     *  String shortSummary():获取秒表总运行时间的简短描述
     *  String prettyPrint():生成一个带有描述所有执行任务的表格的字符串
     *  String toString():生成一个信息字符串，描述所有执行的任务
     */

    @Test
    public void test() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("任务计时测试");
        
        stopWatch.start("任务1");
        Thread.sleep(1000L);
        stopWatch.stop();

        stopWatch.start("任务2");
        Thread.sleep(1500L);
        stopWatch.stop();

        stopWatch.start("任务3");
        Thread.sleep(2000L);
        System.out.println(stopWatch.getLastTaskName() + ",是否正在运行中:" + stopWatch.isRunning());
        stopWatch.stop();

        System.out.println("StopWatch是否正在运行中:" + stopWatch.isRunning());
        // 总计耗时
        System.out.println("总计耗时:" + stopWatch.getTotalTimeMillis() + "毫秒");
        System.out.println("总计耗时:" + stopWatch.getTotalTimeSeconds() + "秒");
        
        // 分别统计每个任务耗时
        Arrays.stream(stopWatch.getTaskInfo()).forEach(sw -> 
                System.out.println(sw.getTaskName() + ",耗时" + sw.getTimeSeconds() + "秒"));
        
        // 打印详细信息
        System.out.println(stopWatch.prettyPrint());
    }
    
    @Test
    public void test2() {
        Set<String> names = new HashSet<>();
        names.add("张三");
        names.add("李四");

        String value = String.join("、", names.stream().collect(Collectors.toSet()));
        System.out.println(value);
    }
    
}
