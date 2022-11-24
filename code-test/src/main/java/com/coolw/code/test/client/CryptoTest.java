package com.coolw.code.test.client;

import cn.hutool.crypto.asymmetric.RSA;
import org.junit.Test;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/6/29 9:07
 * @since 1.0
 */
public class CryptoTest {
    
    @Test
    public void test() {
        RSA rsa = new RSA();

        System.out.println(rsa.getPrivateKey());
        System.out.println(rsa.getPrivateKeyBase64());
    }
}
