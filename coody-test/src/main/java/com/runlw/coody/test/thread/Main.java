package com.runlw.coody.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Classname Main
 * @Description TODO
 * @Author lw
 * @Date 2020-03-25 12:36
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*Thread thread = new Thread(new MyThread());
        thread.start();*/

       /* MyThread1 thread1 = new MyThread1();
        thread1.start();*/

       /*Thread thread = new Thread(){
           @Override
           public void run() {
               System.out.println(Thread.currentThread().getName());
           }
       };
       thread.start();*/

        // lambda
        //new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        /*Callable<String> call = new MyThread2();
        FutureTask<String> ft = new FutureTask<>(call);
        new Thread(ft, "threadName").start();
        System.out.println(ft.get());*/


    }

}
