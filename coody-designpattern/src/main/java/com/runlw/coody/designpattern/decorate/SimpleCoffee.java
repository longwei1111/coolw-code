package com.runlw.coody.designpattern.decorate;

/**
 * @Classname SimpleCoffee
 * @Description 具体组件类, 原味coffee
 * @Author lw
 * @Date 2020-01-13 09:02
 */
public class SimpleCoffee implements Coffee {

    @Override
    public double price() {
        return 1;
    }

    @Override
    public String ingredients() {
        return "coffee";
    }
}
