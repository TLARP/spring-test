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
        System.out.println(threadNew.getName());

        final Thread threadRun = new Thread(new Runnable() {
            public void run() {
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
                System.out.println("thread block"+Thread.currentThread().getName());
                synchronized (ThreadStatusTest.class) {

                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("thread wait"+Thread.currentThread().getName());
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
                    System.out.println("tHREAD TIME WAIT "+Thread.currentThread().getName());
                    Thread.sleep(10000000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
