package com.coolw.code.spring.listener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 事件源
 *
 * @author coolw
 * @date 2022/10/19 10:02
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    
    String orderId;
}
