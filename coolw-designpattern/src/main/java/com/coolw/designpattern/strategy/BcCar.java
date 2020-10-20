package com.coolw.designpattern.strategy;

/**
 * @Classname BcCar
 * @Description 奔驰车具体策略类
 * @Author lw
 * @Date 2019-12-25 12:45
 */
public class BcCar implements CarStrategy {

    @Override
    public void buyCar() {
        System.out.println("欢迎购买奔驰E300L。。。。。。");
    }

}
