package com.coolw.code.thread.spring.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 初始化bean前后，实现一些逻辑
 * 实现BeanPostProcessor接口
 *   1.postProcessBeforeInitialization方法：bean初始化前调用
 *   2.postProcessAfterInitialization方法：bean初始化后调用
 *
 * @author coolw
 * @date 2022/10/13 18:56
 * @since 1.0
 */
@Slf4j
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, "custService")) {
            log.info("beanName:{},初始化前调用......", beanName);
            return bean;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, "custService")) {
            log.info("beanName:{},初始化后调用......", beanName);
            return bean;
        }
        return bean;
    }
}
