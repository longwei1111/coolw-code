package com.coolw.code.thread.spring.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

/**
 * 获取spring容器中的bean: 实现BeanFactoryAware类,重写setBeanFactory方法
 *
 * @author coolw
 * @date 2022/10/13 8:25
 * @since 1.0
 */
@Service
public class PersonService1 implements BeanFactoryAware {
    
    private BeanFactory beanFactory;
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
    
    public void add() {
        CustService custService = (CustService) beanFactory.getBean("custService");
        custService.getCust();
    }
}
