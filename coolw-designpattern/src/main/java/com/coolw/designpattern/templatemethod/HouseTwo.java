package com.coolw.designpattern.templatemethod;

/**
 * @Classname HouseTwo
 * @Description 具体子类房子构建模板two：实现抽象类中所定义的抽象方法和钩子方法
 * @Author lw
 * @Date 2020-01-14 14:37
 */
public class HouseTwo extends HouseTemplate {

    public HouseTwo(String templateName) {
        super(templateName);
    }

    @Override
    protected void buildDoor() {
        System.out.println(templateName + "的门采用木门");
    }

    @Override
    protected void buildWindow() {
        System.out.println(templateName + "的窗户要向南");
    }

    @Override
    protected void buildWall() {
        System.out.println(templateName + "的墙使用玻璃制造");
    }

    @Override
    protected void buildBase() {
        System.out.println(templateName + "的地基使用花岗岩");
    }

    @Override
    protected void buildToilet() {
        System.out.println(templateName + "的厕所建在西北角");
    }
}
