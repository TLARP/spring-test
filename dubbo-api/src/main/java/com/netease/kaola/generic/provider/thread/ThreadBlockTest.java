package com.netease.kaola.generic.provider.thread;

/**
 * Created by hzwangqiqing
 * on 2018/8/5.
 */
public class ThreadBlockTest {
    public static void main(String[] args) {
        final Object lock = new Object();
        Runnable runnable = new Runnable() {
            public void run() {
                for (Integer i = 0; i < 10; i++) {
                    synchronized (lock) {
                        System.out.println(i + Thread.currentThread().getName());
                    }
                }
            }
        };

        Thread threadone = new Thread(runnable);
        threadone.setName("thread1");
        threadone.start();
        Thread threadTwo = new Thread(runnable);
        threadTwo.setName("thread2");
        threadTwo.start();
    }
}
