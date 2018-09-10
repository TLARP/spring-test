package com.netease.kaola.generic.provider.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 */
public class FutureTaskTest {
    public static void main(String[] args) {
        final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(3000L);
                return Thread.currentThread().getName();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("线程1");
                System.out.println("线程1运行");
                futureTask.run();
                try {
                    futureTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1运行完毕");
            }
        }).start();
    }
}
