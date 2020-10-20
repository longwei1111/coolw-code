package com.coolw.designpattern.observer;

/**
 * @Classname PingAnSecurities
 * @Description 具体目标类，平安证券
 * @Author lw
 * @Date 2020-01-09 08:59
 */
public class PingAnSecurities extends Subject {

    @Override
    public void increase() {
        System.out.println("平安证券股票上涨了。。。。。。");
        for (Observer observer : observers) {
            observer.happy();
        }
    }

    @Override
    public void decrease() {
        System.out.println("平安证券股票下跌了。。。。。。");
        for (Observer observer : observers) {
            observer.terrible();
        }
    }
}
