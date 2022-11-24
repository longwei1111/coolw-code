package com.coolw.code.spring.convert;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/13 17:15
 * @since 1.0
 */
@Data
public class User {
    
    private Long id;
    
    private String name;
    
    private Date createTime;
}
