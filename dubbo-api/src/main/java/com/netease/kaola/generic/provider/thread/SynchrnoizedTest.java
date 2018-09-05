package com.netease.kaola.generic.provider.thread;

/**
 * Created by hzwangqiqing on 2018/8/22.
 */
public class SynchrnoizedTest {
    public static void main(String[] args) {
        synchronized (SynchrnoizedTest.class){
            System.out.println("hello world");
        }
    }
}
