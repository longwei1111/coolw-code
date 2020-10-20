package com.coolw.designpattern.abstractfactory.haier;

import com.coolw.designpattern.abstractfactory.FridgeFactory;

/**
 * @Classname MediaFridge
 * @Description 海尔冰箱信息
 * @Author lw
 * @Date 2019-12-24 11:03
 */
public class HaierFridge implements FridgeFactory {

    @Override
    public void fridgeInfo() {
        System.out.println("海尔冰箱牛逼。。。。。。");
    }

}
