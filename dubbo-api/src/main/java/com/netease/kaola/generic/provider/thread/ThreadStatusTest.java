package com.netease.kaola.generic.provider.thread;

import java.lang.management.ManagementFactory;
import java.nio.channels.Pipe;

/**
 * Created by hzwangqiqing
 * on 2018/8/6.
 */
public class ThreadStatusTest {
    static Object lock = new Object();

    public static void main(String[] args) {
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
        Thread threadNew = new Thread();
        threadNew.setName("new Thread");
        System.out.println(threadNew.getName());

        final Thread threadRun = new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("一直运行线程");
                System.out.printf("thread run%s%n", Thread.currentThread().getName());
                while (true) ;
            }
        });
        threadRun.start();

        new Thread(new Runnable() {
            public void run() {
                synchronized (ThreadStatusTest.class) {
                    System.out.println("thread while run"+Thread.currentThread().getName());
                    while (true) ;
                }
            }
        }).start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("阻塞线程");
                System.out.println("thread block"+Thread.currentThread().getName());
                synchronized (ThreadStatusTest.class) {

                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("thread wait"+Thread.currentThread().getName());
                Thread.currentThread().setName("等待线程");
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.currentThread().setName("等待超时线程");
                    System.out.println("tHREAD TIME WAIT "+Thread.currentThread().getName());
                    Thread.sleep(10000000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
