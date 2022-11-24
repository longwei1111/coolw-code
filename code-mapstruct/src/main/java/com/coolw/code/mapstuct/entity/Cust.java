package com.coolw.code.mapstuct.entity;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/9/27 9:04
 * @since 1.0
 */
@Data
public class Cust {

    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;
}
