package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing
 * on 2018/8/7.
 */
public class ConditionTest {
    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();

        final Condition condition = lock.newCondition();



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("thread 1执行并触发等待");
                    condition.await();
                    System.out.println("thread1等待成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("线程1解锁了");
                }
            }
        });
        thread.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("线程三等待");
                    condition.await();
                    System.out.println("线程三获取等待锁");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        thread3.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("线程2执行发送通知信号");
                    condition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("线程2解锁了");
                }
            }
        });
        thread2.start();


        System.out.println("系统结束");

       thread3.interrupt();
        System.out.println("一直被lock");
    }
}
