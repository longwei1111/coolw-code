package com.runlw.coody.designpattern.factorymethod.multi;

/**
 * @Classname ColaFactory
 * @Description 可乐工厂
 * @Author lw
 * @Date 2019-12-21 15:05
 */
public class ColaFactory implements DrinksFactory {

    @Override
    public DrinksProduct product() {
        System.out.println("开始生产可乐。。。。。。");
        return new ColaProduct();
    }

}
