package com.coolw.code.designpattern.proxy.staticproxy;

import com.coolw.code.designpattern.proxy.Ticket;

/**
 * @Classname StaticProxy
 * @Description （买票）静态代理类
 * @Author lw
 * @Date 2019-12-27 08:51
 */
public class StaticProxy implements Ticket {

    private Ticket ticket;

    public StaticProxy(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void buyTicket() {
        System.out.println("代理买票开始");
        ticket.buyTicket();
        System.out.println("代理买票成功");
    }

}
