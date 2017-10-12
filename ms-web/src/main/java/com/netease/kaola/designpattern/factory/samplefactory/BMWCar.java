package com.netease.kaola.designpattern.factory.samplefactory;

import com.netease.kaola.designpattern.factory.Car;

/**
 * Created by hzwangqiqing
 * on 2017/10/12.
 */
public class BMWCar implements Car {
    @Override
    public void whistle() {
        System.out.println("I'm BMW car!");
    }
}
