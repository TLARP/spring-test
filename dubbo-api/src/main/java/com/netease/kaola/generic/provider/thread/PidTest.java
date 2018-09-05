package com.netease.kaola.generic.provider.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

/**
 * Created by hzwangqiqing
 * on 2018/8/22.
 */
public class PidTest {
    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);

        ThreadInfo[] threadInfos = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
        System.out.println(threadInfos);
    }
}
