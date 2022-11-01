package com.coolw.code.thread.demo;

import java.util.concurrent.Exchanger;

/**
 * Exchanger:用于两个线程之间交换数据,支持泛型
 *   1.当一个线程调用exchange方法时,它是处于阻塞的,只有另外一个线程调用exchange方法后,它它才会继续执行
 *   2.exchange方法源码中使用Unsafe类的park/unpark方法实现状态转换的
 *   3.如果有三个线程调用同一个Exchanger实例,则前两个线程会交换数据,第三个线程会进入阻塞状态
 *   4.exchange是可以重复使用的,两个线程之间可以多次进行数据交换
 *   
 * @author coolw
 * @date 2022/11/8 17:07
 * @since 1.0
 */
public class Demo16Exchanger {

    public static void main(String[] args) throws InterruptedException{
        Exchanger<String> exchanger = new Exchanger<>();
        
        new Thread(() -> {
            try {
                System.out.println("当前线程A,得到了另一个线程的数据:" + exchanger.exchange("这是来自线程A的数据"));
            } catch (InterruptedException e ) {
                e.printStackTrace();
            }
        }, "A").start();

        // 当一个线程调用exchange方法时,它是处于阻塞的，只有另外一个线程调用exchange方法后,它它才会继续执行
        // public V exchange(V x, long timeout, TimeUnit unit),设置超时时间,如果超过设置的超时时间会报超时异常
        System.out.println("这个时候线程A是阻塞的,等待线程B的数据");
        Thread.sleep(1000L);

        // 如果注释B线程,A线程不会打印交换的数据(会阻塞等待)
        new Thread(() -> {
            try {
                System.out.println("当前线程B,得到了另一个线程的数据:" + exchanger.exchange("这是来自线程B的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        // 模拟三个线程进行数据交换,前两个线程会正常交换数据且打印,C线程会进入阻塞状态
        Thread.sleep(1000L);
        new Thread(() -> {
            try {
                System.out.println("当前线程C,得到了另一个线程的数据:" + exchanger.exchange("这是来自线程C的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
    
}
