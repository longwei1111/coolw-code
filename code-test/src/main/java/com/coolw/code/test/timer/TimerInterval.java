package com.coolw.code.test.timer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 计时器
 *
 * @author coolw
 * @date 2022/6/28 8:43
 * @since 1.0
 */
public class TimerInterval {
    
    private static final String DEFAULT_ID = "ID";
    private final Map<String, Long> groupMap;
    
    public TimerInterval() {
        groupMap = new ConcurrentHashMap<>();
        //start(TIMER_KEY);
    }
    
    public long start() {
        return start(DEFAULT_ID);
    }
    
    public long start(String id) {
        final Long time = getTime();
        this.groupMap.put(id, time);
        return time;
    }
    
    public long interval() {
        return interval(DEFAULT_ID);
    }

    public long interval(String id) {
        Long lastTime = this.groupMap.get(id);
        if (lastTime == null) {
            return 0;
        }
        return getTime() - lastTime;
    }
    
    public Long getTime() {
        return System.currentTimeMillis();
    }
    
}
