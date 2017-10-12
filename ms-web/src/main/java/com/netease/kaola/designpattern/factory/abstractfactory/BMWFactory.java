package com.netease.kaola.designpattern.factory.abstractfactory;

import com.netease.kaola.designpattern.factory.Car;
import com.netease.kaola.designpattern.factory.samplefactory.BMWCar;

/**
 * Created by hzwangqiqing
 * on 2017/10/12.
 */
public class BMWFactory implements AbstractFactory {
    @Override
    public Car getCar() {
        return new BMWCar();
    }
}
