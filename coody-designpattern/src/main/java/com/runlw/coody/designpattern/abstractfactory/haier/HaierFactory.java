package com.runlw.coody.designpattern.abstractfactory.haier;

import com.runlw.coody.designpattern.abstractfactory.ElectricalAbstractFactory;
import com.runlw.coody.designpattern.abstractfactory.AirConditionerFactory;
import com.runlw.coody.designpattern.abstractfactory.FridgeFactory;

/**
 * @Classname HaierFactory
 * @Description 海尔工厂
 * @Author lw
 * @Date 2019-12-24 11:07
 */
public class HaierFactory implements ElectricalAbstractFactory {

    @Override
    public FridgeFactory createFridge() {
        System.out.println("生产海尔冰箱。。。。。。");
        return new HaierFridge();
    }

    @Override
    public AirConditionerFactory createAirConditioner() {
        System.out.println("生产海尔空调。。。。。。");
        return new HaierAirConditioner();
    }

}
