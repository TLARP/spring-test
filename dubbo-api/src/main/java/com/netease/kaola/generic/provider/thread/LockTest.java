package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing
 * on 2018/8/6.
 */
public class LockTest {
    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.println(lock.isHeldByCurrentThread());

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程获取锁");
                lock.lock();
                lock.unlock();
                System.out.println("线程获取锁陈宫");
            }
        }).start();

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        System.out.println("主线程解锁");
    }
}
