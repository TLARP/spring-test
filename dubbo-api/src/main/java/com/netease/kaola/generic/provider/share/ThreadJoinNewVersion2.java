package com.netease.kaola.generic.provider.share;

/**
 * Created by hzwangqiqing
 * on 2018/9/11.
 * //fixme 下面的做法其实和thread.join达到了相同的效果，不过唯一的区别是我们依赖于线程销毁的通知
 */
public class ThreadJoinNewVersion2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();

        synchronized (thread) {
            try {
                //替代join的一种方法
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程收到通知了！");
        }
    }
}
