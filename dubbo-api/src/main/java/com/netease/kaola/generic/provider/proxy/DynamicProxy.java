package com.netease.kaola.generic.provider.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hzwangqiqing
 * on 2018/8/21.
 */
public class DynamicProxy implements InvocationHandler {
    private Object realObject;

    DynamicProxy(Object object) {
        this.realObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理之前执行的代码");
        return method.invoke(realObject, args);
    }
}
