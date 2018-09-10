package com.netease.kaola.generic.provider.share;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 */
public class ThreadJoinNewVersion {
    public static void main(String[] args) {
        //打印当前进程id(jps命令)->jstatck pid查看对应线程的具体等待信息
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        System.out.println("当前进程id:" + name.split("@")[0]);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程马上要结束了！");
            }
        });
        thread.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (thread){
                        thread.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程1收到通知了！");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (thread){
                        thread.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程2收到通知了！");
            }
        }).start();
    }
}
