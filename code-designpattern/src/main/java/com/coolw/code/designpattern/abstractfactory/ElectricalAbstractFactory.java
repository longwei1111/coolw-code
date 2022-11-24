package com.coolw.code.designpattern.abstractfactory;

/**
 * @Classname ElectricalAbstractFactory
 * @Description 电器抽象工厂
 * @Author lw
 * @Date 2019-12-24 11:05
 */
public interface ElectricalAbstractFactory {

    FridgeFactory createFridge();

    AirConditionerFactory createAirConditioner();

}
