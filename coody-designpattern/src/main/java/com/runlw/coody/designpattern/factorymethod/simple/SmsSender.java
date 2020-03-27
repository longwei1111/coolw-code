package com.runlw.coody.designpattern.factorymethod.simple;

/**
 * @Classname SmsSender
 * @Description 短信发送
 * @Author lw
 * @Date 2019-12-21 15:26
 */
public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("短信发送。。。。。。");
    }

}
