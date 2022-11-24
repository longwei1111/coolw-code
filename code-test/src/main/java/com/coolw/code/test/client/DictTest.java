package com.coolw.code.test.client;

import cn.hutool.core.lang.Dict;
import org.junit.Test;

import java.util.Date;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/6/29 8:41
 * @since 1.0
 */
public class DictTest {

    /**
     * 扩展HashMap无类型区别,Dict支持多个类型
     */
    @Test
    public void test1() {
        Dict dict = Dict.create()
                .set("k1", 1)
                .set("k2", "coolw")
                .set("k3", new Date());
        // 获取指定类型的值
        int v1 = dict.getInt("k1");
        String v2 = dict.getStr("k2");
        Date v3 = dict.getDate("k3");
        System.out.println("v1=" + v1);
        System.out.println("v2=" + v2);
        System.out.println("v3=" + v3);
    }
    
}
