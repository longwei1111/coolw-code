package com.runlw.coody.designpattern.abstractfactory.media;

import com.runlw.coody.designpattern.abstractfactory.AirConditionerFactory;

/**
 * @Classname MediaAirConditioner
 * @Description 美的空调信息
 * @Author lw
 * @Date 2019-12-24 11:02
 */
public class MediaAirConditioner implements AirConditionerFactory {

    @Override
    public void airConditionerInfo() {
        System.out.println("美的空调牛逼。。。。。。");
    }
}
