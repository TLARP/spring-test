package com.netease.kaola.generic.provider.share;

/**
 * Created by hzwangqiqing
 * on 2018/9/11.
 * fixme 这里就是为了说明synchronized在方法级别其实就是这里就是synchronized(this),方便大家快速理解threadjoin的源代码
 */
public class SynchronizedMethodTest {
    public static void main(String[] args) {
        final InnnerClasss innnerClasss = new InnnerClasss();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                innnerClasss.sayHello();
            }
        }).start();

        synchronized (innnerClasss) {
            try {
                System.out.println("主线程马上要等待了！");
                innnerClasss.wait();
                System.out.println("主线程接收到通知了！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class InnnerClasss {
        public synchronized void sayHello() {
            //fixme 这里加一碗迷魂汤
            synchronized (this){
                System.out.println("hello world 这里一个同步方法,执行通知操作！");
                this.notify();
            }
        }
    }
}
