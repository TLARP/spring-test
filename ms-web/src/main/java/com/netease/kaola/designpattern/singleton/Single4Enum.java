package com.netease.kaola.designpattern.singleton;

/**
 * Created by hzwangqiqing
 * on 2017/10/12.
 */
public enum Single4Enum {
    INSTACE(new Singleton3());
    private Singleton3 instance;

    public Singleton3 getInstance() {
        return instance;
    }

    public void setInstance(Singleton3 instance) {
        this.instance = instance;
    }

    Single4Enum(Singleton3 instance) {
        this.instance = instance;
    }
}
