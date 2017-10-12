package com.netease.kaola.multithread;

/**
 * Created by hzwangqiqing on 2017/10/12.
 */
public class RunTest implements Runnable {
    @Override
    public void run() {
        System.out.println("thread is running!");
    }

    public static void main(String[] args) {
        new Thread(new RunTest()).start();
        System.exit(0);
    }
}
