package com.runlw.coody.test.junit;

import com.runlw.coody.test.event.EmailEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Classname ListenerTest
 * @Description 监听测试类
 * @Author lw
 * @Date 2019-12-20 11:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListenerTest {

    @Autowired
    private WebApplicationContext webapplicationcontext;

    @Test
    public void testListener() {
        EmailEvent emailEvent = new EmailEvent("object", "172572575@qq.com", "###listener");
        webapplicationcontext.publishEvent(emailEvent);
    }

}
