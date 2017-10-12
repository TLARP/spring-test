package com.netease.kaola.designpattern.singleton;

/**
 * Created by hzwangqiqing
 * on 2017/10/12.
 * 懒汉
 */
public class Singleton1 {
    private static Singleton1 instance;

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
