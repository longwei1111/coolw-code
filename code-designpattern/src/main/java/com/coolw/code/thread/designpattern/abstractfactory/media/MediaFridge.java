package com.coolw.code.thread.designpattern.abstractfactory.media;

import com.coolw.code.thread.designpattern.abstractfactory.FridgeFactory;

/**
 * @Classname MediaFridge
 * @Description 美的冰箱信息
 * @Author lw
 * @Date 2019-12-24 11:03
 */
public class MediaFridge implements FridgeFactory {

    @Override
    public void fridgeInfo() {
        System.out.println("美的冰箱牛逼。。。。。。");
    }

}
