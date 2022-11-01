package com.coolw.code.spring;

import cn.hutool.core.lang.UUID;
import com.coolw.code.spring.listener.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeSpringApplicationTests {
    
    @Autowired
    private OrderService orderService;
    
    @Test
    public void testOrderEvent() {
        orderService.publishOrderEvent(UUID.fastUUID().toString());
    }
}
