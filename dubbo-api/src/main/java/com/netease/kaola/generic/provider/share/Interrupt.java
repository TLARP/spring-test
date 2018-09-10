package com.netease.kaola.generic.provider.share;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 */
public class Interrupt {
    public static void main(String[] args) {
        Thread threadRun = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer i = 0;
                while (true) {
                    //System.out.println("this thred is always run!");
                    //判断当前状态是否
                    if (Thread.currentThread().isInterrupted()) {
                        //改方法会返回当前是否中断
                        System.out.println(Thread.currentThread().isInterrupted());
                        //改方法会清理中断状态，并返回当前是否中断
                        System.out.println(Thread.interrupted());
                        //返回当前是否中断
                        System.out.println(Thread.currentThread().isInterrupted());
                    }
                }
            }
        });
        threadRun.start();

        //保证后续的操作一定先跑起来
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //fixme 中断上述线程,正在运行的线程中断是没有作用的，只是标注了中断位
        threadRun.interrupt();


        Thread lockSupportThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程马上要park了!");
                LockSupport.park();
                System.out.println("线程被中断唤醒了!");
            }
        });
        lockSupportThread.start();

        //保证后续的操作一定先跑起来
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //fixme 说明中断也可以唤醒线程
        lockSupportThread.interrupt();
    }
}
