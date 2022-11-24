package com.coolw.code.designpattern.strategy;

/**
 * @Classname BmwCar
 * @Description 宝马车具体策略类
 * @Author lw
 * @Date 2019-12-25 12:46
 */
public class BmwCar implements CarStrategy {

    @Override
    public void buyCar() {
        System.out.println("欢迎购买宝马530Li。。。。。。");
    }

}
