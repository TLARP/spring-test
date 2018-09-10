package com.netease.kaola.generic.provider.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing
 * on 2018/9/5.
 */
public class ReentrantLockSetHeadProTest {
    static ReentrantLock reentrantLock = new ReentrantLock(false);

    public static void main(String[] args) {
        reentrantLock.lock();
        System.out.println("hello world0!");
        reentrantLock.lock();
        System.out.println("hello world1");
        reentrantLock.lock();
        System.out.println("hello world2");
    }
}
