package com.netease.kaola.generic.provider.shareaqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 * fixme 看了下面的例子，可以想一下如何去实现futureTask相同的功能
 */
public class LockExample {
    //四个线程都达到了，主线程可以做对应的事情
    static CountDownLatch countDownLatch = new CountDownLatch(4);

    //四次await操作之后，四个线程才可以继续下去
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println("结束后执行最后操作-该线程保证更先执行!");
        }
    });

    public static void main(String[] args) {
        for (Integer i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            System.out.println("主线程马上处于等待状态！");
            countDownLatch.await();
            System.out.println("主线程在等待了四次之后执行了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //循环栅栏锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("线程1马上要被阻塞了!");
                    cyclicBarrier.await();
                    System.out.println("线程1达到了屏障马上执行了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //循环栅栏锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("线程2马上要被阻塞了!");
                    cyclicBarrier.await();
                    System.out.println("线程2达到了屏障马上执行了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
