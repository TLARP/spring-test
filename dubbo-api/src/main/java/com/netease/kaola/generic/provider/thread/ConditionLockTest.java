package com.netease.kaola.generic.provider.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * Created by hzwangqiqing
 * on 2018/8/22.
 */
public class ConditionLockTest implements Condition{
    @Override
    public void await() throws InterruptedException {

    }

    @Override
    public void awaitUninterruptibly() {

    }

    @Override
    public long awaitNanos(long nanosTimeout) throws InterruptedException {
        return 0;
    }

    @Override
    public boolean await(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public boolean awaitUntil(Date deadline) throws InterruptedException {
        return false;
    }

    @Override
    public void signal() {

    }

    public static void main(String[] args) {
        ConditionLockTest conditionLockTest=new ConditionLockTest();
        try {
            conditionLockTest.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello will game over");
        System.out.println("hello world");
    }

    @Override
    public void signalAll() {

    }
}
