package com.netease.kaola.generic.provider.thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hzwangqiqing
 * on 2018/8/21.
 */
public class ProxyTest {
    public static void main(String[] args) {

    }

    private class Handler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }
}
