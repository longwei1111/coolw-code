package com.coolw.designpattern.proxy.cglibdynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Classname CglibProxyFactory
 * @Description （买票）CGLib动态代理
 * @Author lw
 * @Date 2019-12-27 09:02
 */
public class CglibProxyFactory implements MethodInterceptor {

    /**
     * 目标对象
     */
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象创建一个代理对象
     *
     * @return Object
     */
    public Object getProxyInstance() {
        // 1.工具类
        Enhancer en = new Enhancer();
        // 2.设置父类
        en.setSuperclass(target.getClass());
        // 3.设置回调函数
        en.setCallback(this);
        // 4.创建子类(代理对象)
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理买票开始");
        // 执行目标对象的方法
        Object returnValue = method.invoke(target, objects);
        System.out.println("代理买票成功");
        return returnValue;
    }

}
