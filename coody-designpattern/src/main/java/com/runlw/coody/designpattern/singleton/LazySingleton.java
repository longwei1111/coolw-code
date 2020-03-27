package com.runlw.coody.designpattern.singleton;

/**
 * @Classname LazySingleton
 * @Description 懒汉式单例
 * 特点：类加载时没有生成单例，只有当第一次调用 getlnstance 方法时才去创建这个单例
 * @Author lw
 * @Date 2019-12-21 09:09
 */
public class LazySingleton {

    /**
     * 保证instance在所有线程中同步
     */
    private static volatile LazySingleton instance = null;

    /**
     * 私有化构造方法，避免类在外部被实例化
     */
    private LazySingleton() {
    }

    /**
     * 获取实例对象
     *
     * @return instance
     */
    public static synchronized LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
