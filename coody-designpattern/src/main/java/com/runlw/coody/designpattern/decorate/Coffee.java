package com.runlw.coody.designpattern.decorate;

/**
 * @Classname Coffee
 * @Description 抽象组件类, 咖啡
 * @Author lw
 * @Date 2020-01-13 09:01
 */
public interface Coffee {

    /**
     * 价格
     *
     * @return double
     */
    double price();

    /**
     * 配料
     *
     * @return String
     */
    String ingredients();
}
