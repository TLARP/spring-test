package com.netease.kaola.multithread;

/**
 * Created by hzwangqiqing on 2017/10/12.
 */
public class RunTest implements Runnable {
    @Override
    public void run() {
        System.out.println("thread is running! and exit");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunTest());
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.currentThread().sleep(5000L);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        while (true) {
            System.out.println("hello world");
        }
    }
}
