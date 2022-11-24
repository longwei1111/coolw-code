package com.coolw.code.test.client;

import cn.hutool.core.lang.Validator;
import org.junit.Test;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/6/29 8:49
 * @since 1.0
 */
public class ValidatorTest {
    
    @Test
    public void test() {
        boolean email = Validator.isEmail("172572575@qq.com");
        System.out.println("是否为邮箱:" + email);

        boolean mobile = Validator.isMobile("15000994425");
        System.out.println("是否为手机号:" + mobile);

        boolean carVin = Validator.isCarVin("LK7NWD103K1502626");
        System.out.println("是否为vin码:" + carVin);
    }
    
}
