package com.coolw.code.thread.spring.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 项目启动时，加载一些系统参数、完成初始化、预热本地缓存等。
 *   有多个类实现ApplicationRunner接口，需要指定执行顺序，可以使用@Order(n)注解，n的值越小越先执行。也可以通过@Priority注解指定顺序
 * 
 * 两种方式：
 *   1.实现ApplicationRunner接口
 *   2.实现CommandLineRunner接口
 *
 * @author coolw
 * @date 2022/10/13 18:51
 * @since 1.0
 */
@Slf4j
@Component
@Order(3)
public class MyApplicationRunner3 implements ApplicationRunner {
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO 
        log.info("MyApplicationRunner3..........");  
    }
}
