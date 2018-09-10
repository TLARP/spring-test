package com.netease.kaola.generic.provider.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by hzwangqiqing
 * on 2018/9/10.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPool.getInstance();
        ThreadPoolExecutor threadPoolExecutor = threadPool.exec;
        threadPoolExecutor.prestartAllCoreThreads();
        threadPoolExecutor.prestartCoreThread();
        System.out.println(threadPoolExecutor.getPoolSize());
        threadPool.exec(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            System.out.println(threadPoolExecutor.getPoolSize());
        }
    }
}
