package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by hzwangqiqing on 2018/8/7.
 */
public class LockSupportParkTest {
    public static void main(String[] args) {
        final Thread thread = Thread.currentThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(thread);
            }
        }).start();
        LockSupport.park(thread);
        System.out.println(thread.isInterrupted());
        System.out.println(thread.isInterrupted());
    }
}
