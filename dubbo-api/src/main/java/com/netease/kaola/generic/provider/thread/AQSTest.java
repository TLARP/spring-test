package com.netease.kaola.generic.provider.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by hzwangqiqing
 * on 2018/8/6.
 */
public class AQSTest {
    private static final class Sync extends AbstractQueuedSynchronizer {
        int getCount(){
            return getState();
        }
    }
}
