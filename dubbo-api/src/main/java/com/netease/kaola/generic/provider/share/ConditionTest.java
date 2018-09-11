package com.netease.kaola.generic.provider.share;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 * fixme Condition可以理解成一个FIFO的单向链表主要是为了存储等待线程
 * fixme 可以看下下面Condition的构造形式，猜测一下下面的Condition实现类和对应的可重入锁是什么关系，然后自己验证一下？
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
                //fixme 可以想一下这么写unlock会不会触发什么问题？
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
