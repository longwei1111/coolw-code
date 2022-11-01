package com.coolw.code.thread.spring.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/19 10:04
 * @since 1.0
 */
@Service
public class OrderService {
    
    @Autowired
    private ApplicationContext context;
    
    public void publishOrderEvent(String orderId) {
        context.publishEvent(new OrderEvent(orderId));
    }
    
}
