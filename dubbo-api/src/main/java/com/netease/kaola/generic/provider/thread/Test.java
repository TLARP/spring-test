package com.netease.kaola.generic.provider.thread;

/**
 * Created by hzwangqiqing on 2018/8/9.
 */
public class Test {
    private static Object obj = new Object();

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    obj.notify();
                }
            }
        });

        thread.start();

        synchronized (obj) {
            try {
                Thread.sleep(1000L);
                obj.wait();
                System.out.println("获取到了通知");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
