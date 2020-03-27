package com.runlw.coody.test.thread;

import java.util.concurrent.Callable;

/**
 * @Classname MyThread2
 * @Description TODO
 * @Author lw
 * @Date 2020-03-25 13:46
 */
public class MyThread2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName();
    }
}
