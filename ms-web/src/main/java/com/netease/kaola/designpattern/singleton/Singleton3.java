package com.netease.kaola.designpattern.singleton;

/**
 * Created by hzwangqiqing
 * on 2017/10/12.
 */
public class Singleton3 {
    //类初始化加载实例
    private static Singleton3 instance = new Singleton3();

    public Singleton3 getInstance() {
        instance = SingletonHoler.innerInstance;
        return instance;
    }

    //静态代码块，类初始化
    static {
        instance = new Singleton3();
    }

    private static class SingletonHoler {
        private static Singleton3 innerInstance = new Singleton3();
    }
}
