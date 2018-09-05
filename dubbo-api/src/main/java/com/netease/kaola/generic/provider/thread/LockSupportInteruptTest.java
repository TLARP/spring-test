package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by hzwangqiqing
 * on 2018/8/12.
 */
public class LockSupportInteruptTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("马上线程就要被停车了！");
                System.out.println("停车后又执行了");
               for(Integer i=0;i<10000000;i++) {
                   System.out.println(i);
               }
            }
        });
        thread.start();
        LockSupport.park(thread);

    }
}
