package com.coolw.code.designpattern.proxy;

/**
 * @Classname TicketImpl
 * @Description 买票实现类
 * @Author lw
 * @Date 2019-12-27 08:48
 */
public class TicketImpl implements Ticket {

    @Override
    public void buyTicket() {
        System.out.println("买票。。。。。。");
    }

}
