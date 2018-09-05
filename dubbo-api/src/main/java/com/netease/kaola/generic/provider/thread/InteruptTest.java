package com.netease.kaola.generic.provider.thread;

/**
 * Created by hzwangqiqing
 * on 2018/8/7.
 */
public class InteruptTest {

    public static void main(String[] args) {

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行了");
                for (Integer i = 0; i < 100; i++) {
                    System.out.println(i);
                }
                System.out.println("线程执行完毕" + Thread.currentThread().isInterrupted());
            }
        });

        thread1.start();

        thread1.interrupt();
    }
}
