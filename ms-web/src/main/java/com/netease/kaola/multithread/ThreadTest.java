package com.netease.kaola.multithread;

/**
 * Created by hzwangqiqing
 * on 2017/10/12.
 */
public class ThreadTest extends Thread {
    public void run() {
        System.out.println("thread is runing");
        return;
    }

    public static void main(String[] args) {
        new ThreadTest().start();
        System.exit(0);
    }
}
