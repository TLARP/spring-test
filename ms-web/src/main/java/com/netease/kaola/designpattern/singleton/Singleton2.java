package com.netease.kaola.designpattern.singleton;

/**
 * Created by hzwangqiqing
 * on 2017/10/12.
 */
public class Singleton2 {
    private static Singleton2 instance;

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
