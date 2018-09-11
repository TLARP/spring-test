package com.netease.kaola.generic.provider.share;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 * fixme unpark的特点就是先唤醒在停车也照样可以运行
 * fixme  你可以使用jstatck看看对应线程的状态稍微吧下面的sleep时间搞长一点或者把park注释掉
 */
public class SDLockSupport {
    public static void main(String[] args) {
        final Thread mainThread = Thread.currentThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("lockSupport.unpark线程");
                LockSupport.unpark(mainThread);
                System.out.println("唤醒主线程成功!");
            }
        }).start();
        //fixme 这里保证在执行unpark一定先执行，如果这里不加sleep，主线程一般优先于子线程执行，因为优先级更高，但是不是绝对的
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.park();
        System.out.println("主线程收到通知了");
    }
}
