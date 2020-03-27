package com.runlw.coody.test.enums;

/**
 * @Classname CarTypeEnum
 * @Description 汽车类型枚举
 * @Author lw
 * @Date 2020-01-08 11:32
 */
public enum CarTypeEnum {

    CAR_TYPE_BMW("bmw", "宝马"),
    CAR_TYPE_BC("bc", "奔驰"),
    CAR_TYPE_AUDI("audi", "奥迪");

    private String type;

    private String desc;

    private CarTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static String getValue(String type) {
        CarTypeEnum[] carTypeEnums = values();
        for (CarTypeEnum carTypeEnum : carTypeEnums) {
            if (carTypeEnum.type().equals(type)) {
                return carTypeEnum.desc();
            }
        }
        return null;
    }

    public static String getType(String desc) {
        CarTypeEnum[] carTypeEnums = values();
        for (CarTypeEnum carTypeEnum : carTypeEnums) {
            if (carTypeEnum.desc().equals(desc)) {
                return carTypeEnum.type();
            }
        }
        return null;
    }

    private String type() {
        return this.type;
    }

    private String desc() {
        return this.desc;
    }

    public static void main(String[] args) {
        // 根据类型获取描述
        String desc = CarTypeEnum.getValue("bmw");
        System.out.println("根据类型获取描述：" + desc);
        // 根据描述获取类型
        String type = CarTypeEnum.getType("宝马");
        System.out.println("根据描述获取类型：" + type);
    }
}
