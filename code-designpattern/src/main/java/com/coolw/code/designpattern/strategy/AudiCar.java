package com.coolw.code.designpattern.strategy;

/**
 * @Classname AudiCar
 * @Description 奥迪车具体策略类
 * @Author lw
 * @Date 2019-12-25 12:46
 */
public class AudiCar implements CarStrategy {

    @Override
    public void buyCar() {
        System.out.println("欢迎购买奥迪A6L。。。。。。");
    }

}
