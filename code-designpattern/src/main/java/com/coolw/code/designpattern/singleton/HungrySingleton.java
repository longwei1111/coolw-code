package com.coolw.code.designpattern.singleton;

/**
 * @Classname HungrySingleton
 * @Description 饿汉式单例
 * 特点：类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了
 * @Author lw
 * @Date 2019-12-21 09:22
 */
public class HungrySingleton {

    /**
     * 实例化对象
     */
    private static final HungrySingleton instance = new HungrySingleton();

    /**
     * 私有化构造方法，避免类在外部被实例化
     */
    private HungrySingleton() {
    }

    /**
     * 获取实例对象
     *
     * @return instance
     */
    public static HungrySingleton getInstance() {
        return instance;
    }

}
