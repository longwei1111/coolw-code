package com.coolw.code.thread.designpattern.templatemethod;

/**
 * @Classname HouseTemplate
 * @Description 定义抽象类（房子构建模板）：
 * 负责给出一个算法的轮廓和骨架。它由一个模板方法和若干个基本方法构成。
 * @Author lw
 * @Date 2020-01-14 14:18
 */
public abstract class HouseTemplate {

    /**
     * 房子构建模板名称
     */
    protected String templateName;

    protected HouseTemplate(String templateName) {
        this.templateName = templateName;
    }

    /** 模板方法则使用protected修饰，表明其需要在子类中实现 */
    /**
     * 构建门
     */
    protected abstract void buildDoor();

    /**
     * 构建窗户
     */
    protected abstract void buildWindow();

    /**
     * 构建墙
     */
    protected abstract void buildWall();

    /**
     * 构建基地
     */
    protected abstract void buildBase();

    /**
     * 构建厕所
     */
    protected abstract void buildToilet();

    /**
     * 钩子方法：给予授权，判断是否要执行
     *
     * @return boolean
     */
    protected boolean isBuildToilet() {
        return true;
    }

    /**
     * 基本方法：构建房子。用final修饰，保证其不会被子类修改
     */
    public final void buildHouse() {
        buildDoor();
        buildWindow();
        buildWall();
        buildBase();
        if (isBuildToilet()) {
            buildToilet();
        }
    }
}
