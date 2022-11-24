package com.coolw.code.mapstuct.convert;

import com.coolw.code.mapstuct.dto.CarDTO;
import com.coolw.code.mapstuct.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/8/30 8:58
 * @since 1.0
 */
@Mapper
public interface CarConvert {

    CarConvert INSTANCE = Mappers.getMapper(CarConvert.class);

    // 表达式
    @Mapping(target = "time", expression = "java(new java.util.Date())")
    // 设置默认值
    @Mapping(target = "address", source = "address", defaultValue = "上海浦东")
    // 忽略字段映射
    @Mapping(target = "money", ignore = true)
    // 基本字段映射
    @Mapping(target = "name", source = "carName")
    CarDTO carToCarDTO(Car car);
}
