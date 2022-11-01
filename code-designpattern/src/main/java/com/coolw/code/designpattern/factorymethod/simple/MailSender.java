package com.coolw.code.designpattern.factorymethod.simple;

/**
 * @Classname MailSender
 * @Description 邮件发送
 * @Author lw
 * @Date 2019-12-21 15:25
 */
public class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("邮件发送。。。。。。");
    }

}
