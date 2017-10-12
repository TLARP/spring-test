package com.netease.kaola.designpattern.factory.samplefactory;

/**
 * Created by hzwangqiqing
 * on 2017/10/12.
 */
public class BenzCar implements Car{
    @Override
    public void whistle() {
        System.out.println("I'm benz car!");
    }
}
