package com.coolw.code.thread.designpattern.builder;

/**
 * @Classname Director
 * @Description 指挥官
 * @Author lw
 * @Date 2019-12-25 08:45
 */
public class Director {

    public Robot createRobotByDirecotr(IBuildRobot ibr) {
        ibr.buildBody();
        ibr.buildFoot();
        ibr.buildHand();
        ibr.buildHead();
        return ibr.getRobotResult();
    }

}
