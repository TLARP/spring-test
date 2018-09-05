package com.netease.kaola.generic.provider.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by hzwangqiqing
 * on 2018/8/21.
 */
public class Main {
    public static void main(String[] args) {
        SayHelloHandler sayHelloHandler = new SayHelloHandlerImpl();
        InvocationHandler invocationHandler = new DynamicProxy(sayHelloHandler);
        SayHelloHandler sayHelloHandlerProxy = (SayHelloHandler) Proxy.newProxyInstance(SayHelloHandler.class.getClassLoader(), sayHelloHandler.getClass().getInterfaces(), invocationHandler);
        sayHelloHandlerProxy.printClassName();
    }
}
