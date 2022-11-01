package com.coolw.code.mapstuct.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/8/30 8:58
 * @since 1.0
 */
@Data
public class Car {

    private Long id;

    private String carName;

    private String color;
    
    private BigDecimal money;

    private String address;
    
    private int count;
    
    private Map<String, String> map;
    
    private List<String> lsit;
}
