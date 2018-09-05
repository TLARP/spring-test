package com.netease.kaola.generic.provider.proxy;

/**
 * Created by hzwangqiqing
 * on 2018/8/21.
 */
public class SayHelloHandlerImpl implements SayHelloHandler {
    @Override
    public Integer sayHello() {
        System.out.println("say hello is execute!");
        return 1;
    }

    @Override
    public Integer printClassName() {
        System.out.println(this.getClass().getName());
        return 1;
    }
}
