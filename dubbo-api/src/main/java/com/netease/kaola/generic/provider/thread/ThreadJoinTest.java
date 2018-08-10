package com.netease.kaola.generic.provider.thread;

/**
 * Created by hzwangqiqing
 * on 2018/8/5.
 */
public class ThreadJoinTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Integer i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            }
        });
        thread1.start();
        synchronized (thread1) {
            try {
                thread1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("接受到通知");
        }
    }
}
