package com.coolw.code.thread.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * 容器启动和关闭，做一些额外的工作。例如：关闭资源文件等等
 * 实现InitializingBean接口，重写afterPropertiesSet初始化方法
 * 实现DisposableBean接口，重写destroy销毁方法，
 * 
 * @author coolw
 * @date 2022/10/13 19:28
 * @since 1.0
 */
@Slf4j
@Service
public class DService implements InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {   
        log.info("DisposableBean destroy......");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBean afterPropertiesSet......");
    }
}
