package com.runlw.coody.test.listener;

import com.runlw.coody.test.event.EmailEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Classname EmailListener
 * @Description 邮件通知监听
 * @Author lw
 * @Date 2019-12-20 11:10
 */
@Component
public class EmailListener implements ApplicationListener<EmailEvent> {

    @Override
    public void onApplicationEvent(EmailEvent emailEvent) {
        System.out.println("邮件地址：" + emailEvent.getEmail());
        System.out.println("邮件内容：" + emailEvent.getContent());
    }

}

