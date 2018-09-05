package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing
 * on 2018/8/13.
 */
public class RentLockTest {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程0要获取锁了");
                reentrantLock.lock();
                //当第一个线程获取锁之后我们一直独占该锁不释放
                boolean loop = true;
                while (loop) {
                    Integer i = 0;
                    i++;
                }
                reentrantLock.unlock();
            }
        });
        thread.setName("独占线程0");
        thread.start();

        try {
            //保证锁被独占
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1要获取锁了");
                reentrantLock.lock();
                boolean loop = true;
                while (loop) {
                    Integer i = 0;
                    i++;
                }
                reentrantLock.unlock();
            }
        });
        thread1.setName("等待线程1");
        thread1.start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2要获取锁了");
                reentrantLock.lock();
                boolean loop = true;
                while (loop) {
                    Integer i = 0;
                    i++;
                }
                reentrantLock.unlock();
            }
        });
        thread2.setName("等待线程2");
        thread2.start();
    }
}
