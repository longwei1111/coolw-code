package com.coolw.code.thread.mapstuct;

import com.coolw.code.thread.mapstuct.convert.CustConvert;
import com.coolw.code.thread.mapstuct.dto.CustDTO;
import com.coolw.code.thread.mapstuct.entity.Cust;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/9/28 13:08
 * @since 1.0
 */
@Slf4j
@SpringBootTest(classes = CodeMapStructApplication.class)
@RunWith(SpringRunner.class)
public class CodeMapStructApplicationTests {
    
    @Autowired
    private CustConvert custConvert;
    
    @Test
    public void test1() {
        Cust cust = new Cust();
        cust.setId(45);
        cust.setName("张三");
        cust.setCreateTime(new Date());
        cust.setUpdateTime(new Date());

        log.info("cust:{}", cust);
        CustDTO custDTO = custConvert.custToCustDTO(cust);
        log.info("custDTO:{}", custDTO);
    }
}
