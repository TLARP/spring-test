package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hzwangqiqing on 2018/8/6.
 */
public class IntegerTest {
    private static AtomicInteger atomicInteger = new AtomicInteger(10);

    public static void main(String[] args) {
        System.out.println(atomicInteger.getAndSet(20));

        System.out.println(atomicInteger.compareAndSet(19, 25));

        System.out.println(atomicInteger);
    }
}
