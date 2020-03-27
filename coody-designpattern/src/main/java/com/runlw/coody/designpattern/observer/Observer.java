package com.runlw.coody.designpattern.observer;

/**
 * @Classname Observer
 * @Description 抽象观察者
 * @Author lw
 * @Date 2020-01-09 09:01
 */
public interface Observer {

    /**
     * 开心
     */
    public void happy();

    /**
     * 糟糕
     */
    public void terrible();
}
