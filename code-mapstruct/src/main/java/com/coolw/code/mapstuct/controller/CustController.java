package com.coolw.code.mapstuct.controller;

import com.coolw.code.mapstuct.dto.CustDTO;
import com.coolw.code.mapstuct.convert.CustConvert;
import com.coolw.code.mapstuct.entity.Cust;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/9/28 13:30
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/cust")
public class CustController {

    @Autowired
    private CustConvert custConvert;
    
    @GetMapping("/getCust")
    public void getCUST() {
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
