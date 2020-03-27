package com.runlw.coody.designpattern.builder;

/**
 * @Classname IBuildRobot
 * @Description 建造机器人接口
 * @Author lw
 * @Date 2019-12-25 08:39
 */
public interface IBuildRobot {

    /**
     * 建造头
     */
    public void buildHead();

    /**
     * 建造身体
     */
    public void buildBody();

    /**
     * 建造手
     */
    public void buildHand();

    /**
     * 建造脚
     */
    public void buildFoot();

    /**
     * 获取机器人结果
     */
    public Robot getRobotResult();

}
