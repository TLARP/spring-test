package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing
 * on 2018/8/21.
 */
public class RentLockThreadDeadTest {
    //该锁支持单个线程的重复，同时支持不同线程的独占
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println("开始:" + reentrantLock.getHoldCount());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println("thread:" + reentrantLock.getHoldCount());
                throw new IllegalStateException("exception");
            }
        });

        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
            }
        }).start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread:" + reentrantLock.getHoldCount());
        reentrantLock.lock();
        System.out.println("lock success!");
        reentrantLock.unlock();
        System.out.println(reentrantLock.getHoldCount());
    }
}
