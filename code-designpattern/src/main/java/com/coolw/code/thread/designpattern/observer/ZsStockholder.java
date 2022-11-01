package com.coolw.code.thread.designpattern.observer;

/**
 * @Classname ZsStockholder
 * @Description 具体观察者-张三股民
 * @Author lw
 * @Date 2020-01-09 09:04
 */
public class ZsStockholder implements Observer {

    @Override
    public void happy() {
        System.out.println("张三股民开心");
    }

    @Override
    public void terrible() {
        System.out.println("张三股民难受");
    }
}
