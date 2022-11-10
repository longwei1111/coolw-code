package com.coolw.code.thread.pool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * ScheduledExecutorService:延时任务线程池
 *
 * @author coolw
 * @date 2022/11/10 10:07
 * @since 1.0
 */
public class Demo1ScheduledExecutor {

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    
    static class Task implements Runnable {
        private final String taskName;
        
        public Task(String taskName) {
            this.taskName = taskName;
        }
        
        @Override
        public void run() {
            System.out.printf("%s任务执行时间:%s%n", taskName, now());
             /*try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    static class CallTask implements Callable<String> {
        private final String taskName;

        public CallTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String call() throws Exception {
            return taskName;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getName() + ":" + now());
        // 指定任务延时时间:仅执行一次,在2S后执行(无返回值)
        //ScheduledFuture<?> f1 = executor.schedule(new Task("coolw"), 2L, TimeUnit.SECONDS);
        //System.out.println("f1 result:" + f1.get());

        // 指定任务延时时间:仅执行一次,在2S后执行(有返回值)
        //ScheduledFuture<String> f2 = executor.schedule(new CallTask("coolw"), 2L, TimeUnit.SECONDS);
        //System.out.println("f2 result:" + f2.get());
        
        // 按固定频率执行:第一次在5S后执行,后面每2S执行一次,如果当前任务未执行完成,则等待当前任务完成后再执行(无返回值)
        //executor.scheduleAtFixedRate(new Task("coolw"), 5L, 2L, TimeUnit.SECONDS);

        // 按固定延时时间执行:第一次在5S后执行,当前任务完成后延时2S后执行(无返回值)
        executor.scheduleWithFixedDelay(new Task("coolw"), 5L, 2L, TimeUnit.SECONDS);
    }
    
    private static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
}
