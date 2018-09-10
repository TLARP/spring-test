package com.netease.kaola.generic.provider.share;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 */
public class DaemonThread {
    public static void main(String[] args) {
        //fixme finally方法也不一定会执行
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                    throw new RuntimeException("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("final方法执行");
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}
