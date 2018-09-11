package com.netease.kaola.generic.provider.sharequeue;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzwangqiqing
 * on 2018/8/22.
 * 有界队列
 * 1）队列满时，队列添加操作阻塞
 * 2）队列空时，获取线程阻塞
 */
@Data
public class BoundedQueue<T> {
    //保存数组元素的
    private Object[] items;

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition notEmpty = reentrantLock.newCondition();

    private Condition notFull = reentrantLock.newCondition();

    //当前队列中的元素数量
    private int count = 0;

    //下一个待添加元素的位置
    private int addIndex = 0;

    //移除元素的位置
    private int removeIndex = 0;

    BoundedQueue(int size) {
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        reentrantLock.lock();
        //只要数组的容量和数组长度相等，那么对应的线程应该一直处于wait状态
        try {
            while (count == items.length)
                notFull.await();
            items[addIndex] = t;
            //如果发现当前待添加的索引已经达到了最大值，则设置对应值为首位置
            if (addIndex++ == items.length) {
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        reentrantLock.lock();
        try {
            while (count == 0)
                notEmpty.wait();
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) x;
        } finally {
            reentrantLock.unlock();
        }
    }
}
