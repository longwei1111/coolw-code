package com.coolw.code.spring.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 获取spring容器中的bean:实现ApplicationContextAware类,重写setApplicationContext方法
 *
 * @author coolw
 * @date 2022/10/13 8:29
 * @since 1.0
 */
@Service
public class PersonService2 implements ApplicationContextAware {
    
    private ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    public void add() {
        CustService custService = (CustService) applicationContext.getBean("custService");
        custService.getCust();
    }
}
