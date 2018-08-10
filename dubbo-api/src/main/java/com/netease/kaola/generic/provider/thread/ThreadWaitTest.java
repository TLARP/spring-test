package com.netease.kaola.generic.provider.thread;

import javax.sound.midi.Soundbank;
import java.lang.management.ManagementFactory;

/**
 * Created by hzwangqiqing
 * on 2018/8/5.
 */
public class ThreadWaitTest {
    public static void main(String[] args) {
        final Object object = new Object();
        Runnable runnable = new Runnable() {
            public void run() {
                synchronized (object) {
                    try {
                        System.out.println("释放对象锁，供其他代码块通知");
                        object.wait();
                        System.out.println("收到了通知");
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    for (Integer i = 0; i < 10; i++) {
                        System.out.println("wait number " + i);
                    }
                    System.out.println("执行完毕释放锁");
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.setName("testname");
        thread1.start();
        thread1.isAlive();
        System.out.println(thread1.getId());

        System.out.println(ManagementFactory.getRuntimeMXBean().getName());

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (object) {
            System.out.println("线程2获取锁，马上执行通知");
            object.notify();
            try {
                Thread.sleep(50000000000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("测试noify和其它线程的执行顺序关系");
        }
        System.out.println("代码执行完毕");
    }
}
