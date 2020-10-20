package com.coolw.designpattern.templatemethod;

/**
 * @Classname HouseOne
 * @Description 具体子类房子构建模板one：实现抽象类中所定义的抽象方法和钩子方法
 * @Author lw
 * @Date 2020-01-14 14:37
 */
public class HouseOne extends HouseTemplate {

    /**
     * 是否需要构建厕所标志
     */
    public boolean isBuildToilet;

    public HouseOne(String templateName) {
        super(templateName);
    }

    public HouseOne(String templateName, boolean isBuildToilet) {
        this(templateName);
        this.isBuildToilet = isBuildToilet;
    }

    @Override
    protected void buildDoor() {
        System.out.println(templateName + "的门要采用防盗门");
    }

    @Override
    protected void buildWindow() {
        System.out.println(templateName + "的窗户要面向北方");
    }

    @Override
    protected void buildWall() {
        System.out.println(templateName + "的墙使用大理石建造");
    }

    @Override
    protected void buildBase() {
        System.out.println(templateName + "的地基使用钢铁地基");
    }

    @Override
    protected void buildToilet() {
        System.out.println(templateName + "的厕所建在东南角");
    }

    @Override
    protected boolean isBuildToilet() {
        return isBuildToilet;
    }
}
