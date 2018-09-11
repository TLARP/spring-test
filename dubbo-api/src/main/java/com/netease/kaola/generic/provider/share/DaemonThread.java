package com.netease.kaola.generic.provider.share;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 * fixme 1)如果还有非daemon子线程执行则主线程不会停止
 * fixme 2)如果主线程停止，即是有daemon线程，主线程退出daemon线程也会自动退出，可以使用jastack看下java虚拟机的守护线程有哪些？
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
