package com.coolw.code.designpattern.observer;

/**
 * @Classname LsStockholder
 * @Description 具体观察者-李四股民
 * @Author lw
 * @Date 2020-01-09 09:04
 */
public class LsStockholder implements Observer {

    @Override
    public void happy() {
        System.out.println("李四股民开心");
    }

    @Override
    public void terrible() {
        System.out.println("李四股民难受");
    }
}
