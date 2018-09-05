package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing
 * on 2018/8/21.
 */
public class ConditionTest2 {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        final Condition condition=reentrantLock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threa1 start!");
                reentrantLock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
                System.out.println("thread1 end!");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 start!");
                reentrantLock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
                System.out.println("thread2 end!");
            }
        }).start();


        System.out.println("hello");

    }
}
