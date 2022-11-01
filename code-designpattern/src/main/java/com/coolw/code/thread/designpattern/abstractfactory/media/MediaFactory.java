package com.coolw.code.thread.designpattern.abstractfactory.media;

import com.coolw.code.thread.designpattern.abstractfactory.AirConditionerFactory;
import com.coolw.code.thread.designpattern.abstractfactory.ElectricalAbstractFactory;
import com.coolw.code.thread.designpattern.abstractfactory.FridgeFactory;

/**
 * @Classname MediaFactory
 * @Description 美的工厂
 * @Author lw
 * @Date 2019-12-24 11:06
 */
public class MediaFactory implements ElectricalAbstractFactory {

    @Override
    public FridgeFactory createFridge() {
        System.out.println("生产美的冰箱。。。。。。");
        return new MediaFridge();
    }

    @Override
    public AirConditionerFactory createAirConditioner() {
        System.out.println("生产美的空调。。。。。。");
        return new MediaAirConditioner();
    }

}
