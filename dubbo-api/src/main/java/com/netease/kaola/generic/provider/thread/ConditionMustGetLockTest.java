package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing on 2018/8/22.
 */
public class ConditionMustGetLockTest {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Condition condition = reentrantLock.newCondition();

        condition.await();

        System.out.println("hello");
    }

}
