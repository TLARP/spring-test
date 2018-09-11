package com.netease.kaola.generic.provider.share;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 * fixme java线程的停止都是依靠协调而不是依靠强制,比如java线程池的关闭操作根本就不是把线程停掉而是触发中断信号
 * fixme 强制停止线程是安全的容易造成数据不一致，所以这些方法都已经过时
 */
public class Interrupt {
    public static void main(String[] args) {
        Thread threadRun = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer i = 0;
                while (true) {
                    //System.out.println("this thred is always run!");
                    //fixme 下面是我们会用到的几个中断函数
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
