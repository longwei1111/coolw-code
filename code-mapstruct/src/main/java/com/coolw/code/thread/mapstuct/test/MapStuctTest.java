package com.coolw.code.thread.mapstuct.test;

import com.coolw.code.thread.mapstuct.convert.CarConvert;
import com.coolw.code.thread.mapstuct.dto.CarDTO;
import com.coolw.code.thread.mapstuct.entity.Car;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/9/20 8:47
 * @since 1.0
 */
public class MapStuctTest {
    
    @Test
    public void test1() {
        CarConvert mapper = CarConvert.INSTANCE;
        
        Car car = new Car();
        car.setId(123213213L);
        car.setCarName("宝马");
        car.setColor("黑色");
        car.setMoney(new BigDecimal("456000"));
        
        Map<String, String> map = new HashMap<>();
        map.put("a", "123");
        car.setMap(map);

        List<String> list = new ArrayList<>();
        list.add("bbb");
        car.setLsit(list);
        
        CarDTO carDTO = mapper.carToCarDTO(car);
        System.out.println(carDTO);
    }
    
}
