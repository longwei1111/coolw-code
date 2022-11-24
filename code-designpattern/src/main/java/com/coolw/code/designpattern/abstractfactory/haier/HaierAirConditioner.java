package com.coolw.code.designpattern.abstractfactory.haier;

import com.coolw.code.designpattern.abstractfactory.AirConditionerFactory;

/**
 * @Classname MediaAirConditioner
 * @Description 海尔空调信息
 * @Author lw
 * @Date 2019-12-24 11:02
 */
public class HaierAirConditioner implements AirConditionerFactory {

    @Override
    public void airConditionerInfo() {
        System.out.println("海尔空调牛逼。。。。。。");
    }
}
