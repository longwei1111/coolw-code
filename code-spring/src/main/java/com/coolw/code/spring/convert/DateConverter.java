package com.coolw.code.spring.convert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类型转换器(日期)
 * 
 * spring目前支持3中类型转换器：
 *    Converter<S,T>：将 S 类型对象转为 T 类型对象
 *    ConverterFactory<S, R>：将 S 类型对象转为 R 类型及子类对象
 *    GenericConverter：它支持多个source和目标类型的转化，同时还提供了source和目标类型的上下文，这个上下文能让你实现基于属性上的注解或信息来进行类型转换。
 *
 * @author coolw
 * @date 2022/10/13 8:44
 * @since 1.0
 */
@Slf4j
public class DateConverter implements Converter<String, Date> {
    
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public Date convert(String source) {
        if (!StringUtils.hasLength(source)) {
            return null;
        }
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            log.error("日期解析异常,source:{}", source);
            return null;      
        }
    }
    
}
