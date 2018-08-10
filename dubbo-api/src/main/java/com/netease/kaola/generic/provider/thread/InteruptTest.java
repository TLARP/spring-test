package com.netease.kaola.generic.provider.thread;

import static java.lang.Thread.*;

/**
 * Created by hzwangqiqing
 * on 2018/8/7.
 */
public class InteruptTest {

    public static void main(String[] args) {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("gekki");
                    try {
                        sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.start();

        thread1.interrupt();
    }
}
