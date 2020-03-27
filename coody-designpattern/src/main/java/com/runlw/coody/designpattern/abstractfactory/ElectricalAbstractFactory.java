package com.runlw.coody.designpattern.abstractfactory;

/**
 * @Classname ElectricalAbstractFactory
 * @Description 电器抽象工厂
 * @Author lw
 * @Date 2019-12-24 11:05
 */
public interface ElectricalAbstractFactory {

    public FridgeFactory createFridge();

    public AirConditionerFactory createAirConditioner();

}
