package com.netease.kaola.generic.provider.share;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 */
public class ConditionTest {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        final Condition condition = reentrantLock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.lock();
                condition.signal();
                reentrantLock.unlock();
                System.out.println("线程通知完成!");
            }
        }).start();


        try {
            reentrantLock.lock();
            condition.await();
            reentrantLock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程收到通知了！");
    }
}
