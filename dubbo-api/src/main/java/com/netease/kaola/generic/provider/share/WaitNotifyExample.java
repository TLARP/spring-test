package com.netease.kaola.generic.provider.share;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 */
public class WaitNotifyExample {
    static Object obj = new Object();

    /**
     * TODO
     * FIXME 1)多个线程都可以使用synchronized关键字，那么猜一猜你需要什么样的数据结构来存贮这些线程？
     * FIXME 2)如果让我们自己取维护线程和该数据结构的关系，你需要考虑哪些问题？
     */
    public static void main(String[] args) {
        //打印当前进程id(jps命令)->jstatck pid查看对应线程的具体等待信息
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        System.out.println("当前进程id:" + name.split("@")[0]);


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    Thread.currentThread().setName("线程1");
                    try {
                        System.out.println("线程1马上要进入等待状态,调用wait方法会使当前代码块释放同步代码块的锁!");
                        obj.wait();
                        System.out.println("线程1接受到了通知");
                        synchronized (obj) {
                            System.out.println("同一个线程对obj重复加锁,外部锁未释放，但还是可以重复加锁，说明该锁是可重入锁!");
                        }
                        System.out.println("线程1执行完毕");
                    } catch (InterruptedException e) {
                        System.out.println("说明同步关键字支持响应中断，并给我们提供了清理相关数据的入口！");
                    }
                }
            }
        }).start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("线程2");
                synchronized (obj) {
                    System.out.println("线程2马上进入等待,调用wait方法会使当前代码块释放同步代码块的锁!");
                    try {
                        obj.wait();
                        System.out.println("线程2接受到了通知");
                    } catch (InterruptedException e) {
                        System.out.println("说明同步关键字支持响应中断，并给我们提供了清理相关数据的入口！");
                    }
                }
            }
        });
        thread2.start();

        //fixme  保证通知操作一定晚于wait操作,如果先通知后wait则会永远等待
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //fixme 这里注释掉是为了使用jstack命令时可以看到对应是等待状态，因为一直没收到通知
        /*synchronized (obj) {
            obj.notify();
            obj.notifyAll();
        }*/
    }
}
