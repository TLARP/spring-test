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
                Thread.sleep(3000L);
                throw new RuntimeException();
            }
        });

        futureTask.run();

        try {
            String value = futureTask.get();
            System.out.println("value:" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
