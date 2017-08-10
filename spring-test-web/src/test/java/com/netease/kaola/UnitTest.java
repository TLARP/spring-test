package com.netease.kaola;

import com.netease.kaola.base.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by hzwangqiqing
 * on 2017/8/10.
 */

public class UnitTest extends BaseTest {
    @Resource
    HelloCompose helloCompose;

    @Test
    public void helloCompose_sayHello() {
        System.out.println(helloCompose.sayHello());
    }
}
