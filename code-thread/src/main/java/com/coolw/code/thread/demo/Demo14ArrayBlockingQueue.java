package com.coolw.code.thread.demo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/11/8 8:56
 * @since 1.0
 */
public class Demo14ArrayBlockingQueue {
    
    private final int queueSize = 10;
    private final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);

    public static void main(String[] args) throws InterruptedException {
        Demo14ArrayBlockingQueue abq = new Demo14ArrayBlockingQueue();
        Producer producer = abq.new Producer();
        Consumer consumer = abq.new Consumer();
        
        producer.start();
        Thread.sleep(50L);
        consumer.start();
    }
    
    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Integer value = queue.take();
                    System.out.println("从队列中取出一个元素为[" + value + "],队列剩余:" + queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    class Producer extends Thread {
        @Override
        public void run() {
            int count = 1;
            while (true) {
                if (count > queueSize) {
                    try {
                        // 休眠1S,方便查看结果
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    queue.put(count);
                    System.out.println("向队列中放入一个元素为[" + count + "],队列已有:" + queue.size() + "个元素");
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
