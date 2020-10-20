package com.coolw.designpattern.factorymethod.multi;

/**
 * @Classname SpriteFactory
 * @Description 雪碧工厂
 * @Author lw
 * @Date 2019-12-21 15:05
 */
public class SpriteFactory implements DrinksFactory {

    @Override
    public DrinksProduct product() {
        System.out.println("开始生产雪碧。。。。。。");
        return new SpriteProduct();
    }

}
