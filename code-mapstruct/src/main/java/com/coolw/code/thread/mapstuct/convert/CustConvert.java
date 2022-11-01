package com.coolw.code.thread.mapstuct.convert;

import com.coolw.code.thread.mapstuct.dto.CustDTO;
import com.coolw.code.thread.mapstuct.entity.Cust;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * spring管理
 *
 * @author coolw
 * @date 2022/9/27 9:07
 * @since 1.0
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustConvert {
 
    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updateTime", source = "updateTime", dateFormat = "yyyy-MM-dd")
    CustDTO custToCustDTO(Cust cust); 
}
