package com.netease.kaola.generic.provider.thread;

import java.lang.management.ManagementFactory;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by hzwangqiqing
 * on 2018/8/12.
 */
public class LuckSupportParkTest {
    public static void main(String[] args) {
        System.out.println("线程开始运行了");
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
        Thread.currentThread().setName("测试");
        LockSupport.park();
        System.out.println("系统结束了");
    }
}
