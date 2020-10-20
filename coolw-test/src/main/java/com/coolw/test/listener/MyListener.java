package com.coolw.test.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Classname MyListener
 * @Description 事件监听
 * @Author lw
 * @Date 2019-12-20 11:01
 */
@Component
public class MyListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器中初始化Bean数量:" + event.getApplicationContext().getBeanDefinitionCount());
    }

}
