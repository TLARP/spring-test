package com.netease.kaola.generic.provider.thread;

/**
 * Created by hzwangqiqing
 * on 2018/8/22.
 */
public class ThreadDeadTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("测试Thread");
                System.out.println("线程结束");
            }
        }).start();

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       while (true){
           System.out.println("hello world");
       }
    }
}
