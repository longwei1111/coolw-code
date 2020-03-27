package com.runlw.coody.designpattern.proxy.jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Classname JdkProxyFactory
 * @Description （买票）JDK代理工厂
 * @Author lw
 * @Date 2019-12-27 08:57
 */
public class JdkProxyFactory {

    /**
     * 目标对象
     */
    private Object target;

    public JdkProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理买票开始");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("代理买票成功");
                        return returnValue;
                    }
                }
        );
    }

}
