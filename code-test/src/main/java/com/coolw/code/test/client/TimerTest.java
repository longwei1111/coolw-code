package com.coolw.code.test.client;

import com.coolw.code.test.timer.TimerInterval;
import org.junit.Test;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/6/29 8:37
 * @since 1.0
 */
public class TimerTest {
    
    @Test
    public void test() throws InterruptedException {
        TimerInterval timer = new TimerInterval();
        timer.start();

        Thread.sleep(1000L);

        long interval = timer.interval();
        System.out.println("耗时:" + interval + "ms");
    }
    
}
