package com.coolw.code.designpattern.builder;

/**
 * @Classname Robot
 * @Description 机器人模型
 * @Author lw
 * @Date 2019-12-25 08:38
 */
public class Robot {

    /**
     * 头
     */
    private String head;
    /**
     * 身体
     */
    private String body;
    /**
     * 手
     */
    private String hand;
    /**
     * 脚
     */
    private String foot;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }
}
