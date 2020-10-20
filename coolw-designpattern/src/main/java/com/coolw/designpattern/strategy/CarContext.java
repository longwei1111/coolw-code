package com.coolw.designpattern.strategy;

/**
 * @Classname CarContext
 * @Description 汽车环境类
 * @Author lw
 * @Date 2019-12-25 12:49
 */
public class CarContext {

    private CarStrategy carStrategy;

    public CarContext(CarStrategy carStrategy) {
        this.carStrategy = carStrategy;
    }

    public void buyCarStrategy() {
        carStrategy.buyCar();
    }

}
