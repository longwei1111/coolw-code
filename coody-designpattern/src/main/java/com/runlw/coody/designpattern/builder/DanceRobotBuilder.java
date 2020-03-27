package com.runlw.coody.designpattern.builder;

/**
 * @Classname DanceRobotBuilder
 * @Description 建造跳舞机器人
 * @Author lw
 * @Date 2019-12-25 08:43
 */
public class DanceRobotBuilder implements IBuildRobot {

    private Robot robot;

    public DanceRobotBuilder() {
        robot = new Robot();
    }

    @Override
    public void buildHead() {
        robot.setHead("写入机械舞程序");
    }

    @Override
    public void buildBody() {
        robot.setBody("钛合金身体");
    }

    @Override
    public void buildHand() {
        robot.setHand("钛合金手");
    }

    @Override
    public void buildFoot() {
        robot.setFoot("钛合金脚");
    }

    @Override
    public Robot getRobotResult() {
        return robot;
    }
}
