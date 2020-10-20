package com.coolw.test.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Classname EmailEvent
 * @Description 邮件通知事件
 * @Author lw
 * @Date 2019-12-20 11:05
 */
public class EmailEvent extends ApplicationEvent {

    private String email;

    private String content;

    public EmailEvent(Object source) {
        super(source);
    }

    public EmailEvent(Object source, String email, String content) {
        super(source);
        this.email = email;
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
