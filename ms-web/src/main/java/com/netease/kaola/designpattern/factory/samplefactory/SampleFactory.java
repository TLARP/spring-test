package com.netease.kaola.designpattern.factory.samplefactory;

import com.netease.kaola.designpattern.factory.Car;

/**
 * Created by hzwangqiqing
 * on 2017/10/12.
 */
public class SampleFactory {
    public Car getCar(Integer type) {
        if (type == 0) {
            return new BMWCar();
        }
        if (type == 1) {
            return new BenzCar();
        }
        return null;
    }
}
