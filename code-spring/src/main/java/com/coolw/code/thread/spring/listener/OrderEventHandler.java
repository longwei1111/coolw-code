package com.coolw.code.thread.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事件处理器
 *
 * @author coolw
 * @date 2022/10/19 10:03
 * @since 1.0
 */
@Slf4j
@Component
public class OrderEventHandler {
    
    @EventListener
    public void orderEventHandle(OrderEvent orderEvent) {
        log.info("监听订单下单,单号:{}", orderEvent.getOrderId());    
    }
    
}
