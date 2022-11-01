package com.coolw.code.spring.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * 获取spring容器中的bean:实现ApplicationListener类,重写onApplicationEvent方法
 *
 * @author coolw
 * @date 2022/10/13 8:34
 * @since 1.0
 */
@Service
public class PersonService3 implements ApplicationListener<ContextRefreshedEvent> {
    
    private ApplicationContext applicationContext;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        applicationContext = contextRefreshedEvent.getApplicationContext();
    }
    
    public void add() {
        CustService custService = (CustService) applicationContext.getBean("custService");
        custService.getCust();
    }
}
