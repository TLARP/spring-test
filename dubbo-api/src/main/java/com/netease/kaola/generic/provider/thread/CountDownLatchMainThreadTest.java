package com.netease.kaola.generic.provider.thread;

import java.nio.channels.Pipe;
import java.util.concurrent.CountDownLatch;

/**
 * Created by hzwangqiqing
 * on 2018/8/5.
 */
public class CountDownLatchMainThreadTest {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程1马上进入等待状态");
                    countDownLatch.await();
                    System.out.println("线程1执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();

        Thread thread2=new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程2马上进入等待状态");
                    countDownLatch.await();
                    System.out.println("线程2执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread2.start();

        countDownLatch.countDown();
        countDownLatch.countDown();
        System.out.println("main thred over");
    }
}
