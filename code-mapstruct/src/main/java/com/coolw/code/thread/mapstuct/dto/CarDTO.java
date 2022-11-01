package com.coolw.code.thread.mapstuct.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
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
public class CarDTO {
    
    private Long id;
    
    private String name;
    
    private String color;
    
    private BigDecimal money;
    
    private String address;
    
    private Date time;
    
    private String count;

    private Map<String, String> map;

    private List<String> lsit;
}
