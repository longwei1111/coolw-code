package com.runlw.coody.designpattern.abstractfactory.media;

import com.runlw.coody.designpattern.abstractfactory.ElectricalAbstractFactory;
import com.runlw.coody.designpattern.abstractfactory.AirConditionerFactory;
import com.runlw.coody.designpattern.abstractfactory.FridgeFactory;

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
