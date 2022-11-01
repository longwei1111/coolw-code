package com.coolw.code.test.thread.demo;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/26 11:07
 * @since 1.0
 */
public class Demo3 {

    public static void main(String[] args) {
        System.out.println(test(24));
    }
    
    public static Thread.State test( int var0) {
        if ((var0 & 4) != 0) {
            return Thread.State.RUNNABLE;
        } else if ((var0 & 1024) != 0) {
            return Thread.State.BLOCKED;
        } else if ((var0 & 16) != 0) {
            return Thread.State.WAITING;
        } else if ((var0 & 32) != 0) {
            return Thread.State.TIMED_WAITING;
        } else if ((var0 & 2) != 0) {
            return Thread.State.TERMINATED;
        } else {
            return (var0 & 1) == 0 ? Thread.State.NEW : Thread.State.RUNNABLE;
        }
    }
}
