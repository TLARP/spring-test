package com.netease.kaola.generic.provider.share;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 */
public class ThreadJoin {
    public static void main(String[] args) {
        //打印当前进程id(jps命令)->jstatck pid查看对应线程的具体等待信息
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        System.out.println("当前进程id:" + name.split("@")[0]);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("无限循环线程");
                while (true){
                   //
                }
            }
        });
        thread.start();

        //主线程等待子线程结束后才能返回
        Thread.currentThread().setName("主线程");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
