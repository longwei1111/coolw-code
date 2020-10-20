package com.coolw.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Subject
 * @Description 定义抽象目标类
 * @Author lw
 * @Date 2020-01-09 08:53
 */
public abstract class Subject {

    /**
     * 定义观察者集合,用于管理观察者
     */
    public List<Observer> observers = new ArrayList<>();

    /**
     * 向观察者集合中添加观察者对象
     *
     * @param observer
     */
    public void add(Observer observer) {
        observers.add(observer);
    }

    /**
     * 向观察者集合中删除观察者对象
     *
     * @param observer
     */
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知观察者抽象方法,股票上涨
     */
    public abstract void increase();

    /**
     * 通知观察者抽象方法,股票下跌
     */
    public abstract void decrease();
}
