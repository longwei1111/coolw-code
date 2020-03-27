package com.runlw.coody.designpattern.factorymethod.multi;

/**
 * @Classname ColaProduct
 * @Description 可乐产品
 * @Author lw
 * @Date 2019-12-21 14:30
 */
public class ColaProduct implements DrinksProduct {

    @Override
    public void show() {
        System.out.println("可乐产品显示。。。。。。");
    }

}
