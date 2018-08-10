package com.netease.kaola.generic.provider.aqs;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by hzwangqiqing
 * on 2018/8/6.
 */
public class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements Serializable {

    private static final long serialVersionUID = 7373984972572414691L;

    protected AbstractQueuedSynchronizer() {

    }

    static final class Node {
        static final Node SHARED = new Node();

        static final Node EXCLUSIVE = null;

        static final int CANCELLED = 1;

        static final int SIGNAL = -1;

        static final int CONDITION = -2;

        static final int PROPAGATE = -3;

        volatile int waitStatus;

        volatile Node prev;

        volatile Node next;

        volatile Thread thread;

        Node nextWaiter;

        final boolean isShared() {
            return nextWaiter == SHARED;
        }

        final Node predecessor() throws NullPointerException {
            Node node = prev;
            if (node == null) {
                throw new NullPointerException();
            }
            return node;
        }

        Node() {

        }

        Node(Thread thread, Node node) {
            this.nextWaiter = node;
            this.thread = thread;
        }

        Node(int waitStatus, Thread thread) {
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }

    private transient volatile Node head;

    private transient volatile Node tail;

    private volatile int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private static Unsafe unsafe;

    static Long stateOffset;
    static Long tailOffset;
    static Long headOffset;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            stateOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("state"));
            tailOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            headOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("head"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    protected final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    static final Long spinForTimeoutThreshold = 1000L;


    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);

        Node prev = tail;
        if (prev != null) {
            node.prev = prev;
            if (compareAndSetTail(prev, node)) {
                prev.next = node;
                return node;
            }
        }
        enq(node);
        return node;
    }

    /**
     * 使用cas算法插入一个节点到尾部
     */
    private Node enq(final Node node) {
        for (; ; ) {
            Node t = tail;
            if (t == null) {
                if (compareAndSetHead(new Node())) {
                    tail = head;
                }
            } else {
                node.prev = t;
                if (compareAndSetTail(tail, node)) {
                    t.next = node;
                    return node;
                }
            }
        }
    }

    private boolean compareAndSetHead(Node node) {
        return unsafe.compareAndSwapObject(this, headOffset, null, node);
    }

    private boolean compareAndSetTail(Node expect, Node node) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, node);
    }

    public static void main(String[] args) {
        AbstractQueuedSynchronizer abstractQueuedSynchronizer = new AbstractQueuedSynchronizer();
        Thread thread = new Thread();
        abstractQueuedSynchronizer.addWaiter(Node.SHARED);
        System.out.println(abstractQueuedSynchronizer.head);
    }
}
