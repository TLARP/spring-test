package com.netease.kaola.generic.provider.thread;

/**
 * Created by hzwangqiqing on 2018/8/22.
 */
public class SynnizeTest {
    static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("thread1 start");
                    while (true) ;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("thread2 start");
                }
            }
        }).start();

        System.out.println("game will over,but sub thread is going!");
    }
}
