package com.coolw.designpattern.factorymethod.multi;

/**
 * @Classname SpriteProduct
 * @Description 雪碧产品
 * @Author lw
 * @Date 2019-12-21 14:31
 */
public class SpriteProduct implements DrinksProduct {

    @Override
    public void show() {
        System.out.println("雪碧产品显示。。。。。。");
    }

}
