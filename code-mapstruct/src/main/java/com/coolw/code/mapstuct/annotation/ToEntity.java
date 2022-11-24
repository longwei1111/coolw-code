package com.coolw.code.mapstuct.annotation;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * entity 共同属性映射注解
 *
 * @author coolw
 * @date 2022/9/27 8:59
 * @since 1.0
 */
@Retention(RetentionPolicy.CLASS)
@Mapping(target = "id", ignore = true)
@Mapping(target = "createdTime", expression = "java(new java.util.Date())")
public @interface ToEntity {
    
}
