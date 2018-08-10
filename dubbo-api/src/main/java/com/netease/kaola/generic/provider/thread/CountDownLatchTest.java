package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hzwangqiqing
 * on 2018/8/5.
 */
public class CountDownLatchTest {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {


        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("线程1执行，并等待锁");
                countDownLatch.countDown();
            }
        });

        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("线程2执行，并等待锁");
                countDownLatch.countDown();
                System.out.println("线程2已经执行了");
            }
        });
        thread2.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("等待成功，执行完毕");
    }
}
