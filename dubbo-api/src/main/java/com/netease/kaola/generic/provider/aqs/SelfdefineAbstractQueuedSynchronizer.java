package com.netease.kaola.generic.provider.aqs;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by hzwangqiqing
 * on 2018/8/28.
 */
public abstract class SelfdefineAbstractQueuedSynchronizer implements Serializable {
    //先声明一个内部类
    static final class Node {
        //共享模式
        static final Node SHARED = new Node();

        //排他模式
        static final Node EXCLUSIVE = null;

        //waitStatus-取消状态
        static final int CANCELLED = 1;

        //后续节点需要通知
        static final int SIGNAL = -1;

        //线程处于等待Condition-signal状态
        static final int CONDITION = -2;

        //后续节点需要无条件唤醒-说明处于共享模式
        static final int PROPAGATE = -3;

        //节点的等待状态，包含在上述节点值的范围中
        volatile int waitStatus;

        //当前节点的前驱节点
        volatile Node prev;

        //当前节点的后续节点
        volatile Node next;

        //当前节点所在的线程
        volatile Thread thread;

        //节点等待模式
        Node nextWaiter;

        //判断当前是否是共享模式
        final boolean isShared() {
            return SHARED == nextWaiter;
        }

        //返回当前节点的前驱节点
        Node predecessor() throws NullPointerException {
            if (prev == null) {
                throw new NullPointerException();
            }
            return prev;
        }

        //节点默认构造函数
        Node() {

        }

        //通过线程和节点模式构造节点
        Node(Thread thread, Node mode) {
            this.thread = thread;
            this.nextWaiter = mode;
        }

        //通过线程和节点等待状态构造节点
        Node(Thread thread, int waitStatus) {
            this.thread = thread;
            this.waitStatus = waitStatus;
        }
    }

    /*******************************************************************************************************************
     * 获取AQS占有线程
     *******************************************************************************************************************/
    private transient Thread exclusiveOwnerThread;

    //设置独占线程
    protected final void setExclusiveOwnerThread(Thread t) {
        exclusiveOwnerThread = t;
    }

    //获取当前占有线程
    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    /*******************************************************************************************************************
     * 绕过jdk安全限制获取Unsafe实例
     *******************************************************************************************************************/
    //支持cas和其它操作的内存类
    static Unsafe unsafe;

    //头节点字段对象偏移量
    private static Long headOffset;

    //尾结点对象偏移量
    private static Long tailOffset;

    //状态值对象偏移量
    private static Long stateOffset;

    //节点等待状态的偏移量
    private static Long waitStatusOffset;

    //节点下一个节点链接
    private static Long nextOffset;

    static final long spinForTimeoutThreshold = 1000L;

    //静态初始化对应字段的初始值
    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            stateOffset = unsafe.objectFieldOffset(SelfdefineAbstractQueuedSynchronizer.class.getDeclaredField("state"));
            tailOffset = unsafe.objectFieldOffset(SelfdefineAbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            headOffset = unsafe.objectFieldOffset(SelfdefineAbstractQueuedSynchronizer.class.getDeclaredField("head"));
            waitStatusOffset = unsafe.objectFieldOffset(SelfdefineAbstractQueuedSynchronizer.Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset(SelfdefineAbstractQueuedSynchronizer.Node.class.getDeclaredField("next"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /*******************************************************************************************************************
     * AQS数据结构和cas操作
     *******************************************************************************************************************/
    private static final long serialVersionUID = 2821912105093080492L;

    //只能在子类中调用
    protected SelfdefineAbstractQueuedSynchronizer() {

    }

    //同步队列-头结点
    volatile Node head;

    //同步队列-尾部节点
    volatile Node tail;

    //控制锁的int核心字段
    private volatile int state;

    //设置状态值
    public void setState(int state) {
        this.state = state;
    }

    //获取状态值
    public int getState() {
        return this.state;
    }

    //试用cas的方式更新state-更新成功返回true
    protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    //使用cas更新同步队列尾节点，更新失败返回true
    protected final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    //使用cas设置头结点
    protected final boolean compareAndSetHead(Node node) {
        return unsafe.compareAndSwapObject(this, headOffset, null, node);
    }

    /**
     * 使用cas的方式修改节点的next属性
     *
     * @param node   执行节点
     * @param expect 期待节点
     * @param update 更新节点
     */
    private static final boolean compareAndSetNext(Node node,
                                                   Node expect,
                                                   Node update) {
        return unsafe.compareAndSwapObject(node, nextOffset, expect, update);
    }

    /**
     * @param node   当前节点
     * @param expect 期待的更新int值
     * @param update 更新后的值
     */
    private static boolean compareAndSetWaitStatus(Node node,
                                                   int expect,
                                                   int update) {
        return unsafe.compareAndSwapInt(node, waitStatusOffset,
                expect, update);
    }

    /*******************************************************************************************************************
     * 需要子类实现的方法
     *******************************************************************************************************************/
    //fixme 这里直接抛出不支持操作异常，表明这个方法时需要被重写的方法
    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }

    //fixme 独占模式下的锁释放
    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }

    //fixme 共享模式下的锁释放
    protected boolean tryReleaseShared(int arg) {
        throw new UnsupportedOperationException();
    }

    //fixme 共享模式下如何获取状态码，直接抛出异常表明该方法需要被重写
    protected int tryAcquireShared(int arg) {
        throw new UnsupportedOperationException();
    }

    /*******************************************************************************************************************
     * 模板方法模式
     *******************************************************************************************************************/
    //fixme 插入一个节点的模板方法，这里只是使用模板方法模式的一个变种
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
                acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) {
            selfInterrupt();
        }
    }

    /**
     * 可中断的获取独占锁
     *
     * @param arg 独占资源数量
     */
    public final void acquireInterruptibly(int arg) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        doAcquireInterruptibly(arg);
    }

    /**
     * 在指定时间内获取锁，超时自动取消
     *
     * @param arg          独占资源数
     * @param nanosTimeout 超时纳秒数
     */
    public final boolean tryAcquireNanos(int arg, long nanosTimeout) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return tryAcquire(arg) || doAcquireNanos(arg, nanosTimeout);
    }

    /**
     * 共享模式获取的模板方法
     *
     * @param arg 获取参数
     */
    public final void acquireShared(int arg) {
        //小于0说明灭有获取到锁
        if (tryAcquireShared(arg) < 0)
            doAcquireShared(arg);
    }

    /*******************************************************************************************************************
     * 自旋
     *******************************************************************************************************************/
    //添加一个节点到等待队列当中
    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        Node pred = tail;
        //先快速试一次
        if (tail != null) {
            //先去更新当前添加节点的前驱节点，即是添加不成功，后续还是可以覆盖
            node.prev = pred;
            //如果设置成功了
            if (compareAndSetTail(pred, node)) {
                //fixme 如果线程在这里阻塞，也就是当前节点链接到了前驱节点，但是前驱的尾巴节点没有链接到当前节点
                node.next = pred;
                return node;
            }
        }
        //使用cas自旋插入
        enq(node);
        return node;
    }

    //使用自旋插入同步队列
    private Node enq(Node node) {
        for (; ; ) {
            Node pred = tail;
            //如果发现节点为空
            if (pred == null) {
                //插入一个标杆节点
                if (compareAndSetHead(new Node())) {
                    //如果设置成功了，尾巴节点也要更新
                    tail = head;
                }
            } else {
                //跟上面的快速插入代码一样
                node.prev = pred;
                if (compareAndSetTail(pred, node)) {
                    pred.next = node;
                    return node;
                }
            }
        }
    }

    //取消节点，此时节点已经入队列了
    private void cancelAcquire(Node node) {
        //TODO 节点是否可以为NULL这里没有想出对应的case
        if (node == null) {
            return;
        }
        node.thread = null;//help gc
        Node pred = node.prev;
        //TODO 这里会不会有空指针没想通
        while (pred.waitStatus > 0) {
            node.prev = pred = pred.prev;
        }
        //上述操作会产生跳链
        Node predNext = pred.next;
        //更新为取消状态
        node.waitStatus = Node.CANCELLED;
        if (node == tail && compareAndSetTail(node, pred)) {
            compareAndSetNext(pred, predNext, null);
        } else {
            //TODO 没看懂
            int ws;
            if (pred != head &&
                    ((ws = pred.waitStatus) == Node.SIGNAL ||
                            (ws <= 0 && compareAndSetWaitStatus(pred, ws, Node.SIGNAL))) &&
                    pred.thread != null) {
                Node next = node.next;
                if (next != null && next.waitStatus <= 0)
                    compareAndSetNext(pred, predNext, next);
            } else {
                unparkSuccessor(node);
            }
            node.next = node; // help GC
        }
    }

    /**
     * 判断在获取失败之后石头应该停车Lock.park(this)
     *
     * @param pred 前驱节点
     * @param node 后继节点
     * @return 返回是否应该停车
     */
    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        //获取前驱节点的状态,进行到这里说明前驱节点一定不为空
        int waitStatus = pred.waitStatus;
        //fixme 如果前驱节点的状态是通知，那么可以安心睡觉了，因为前驱节点退出时有唤醒我的责任
        if (waitStatus == Node.SIGNAL) {
            return true;
        }
        //如果是取消状态
        if (waitStatus > 0) {
            //通过循环一直找到第一个不是停车的节点
            do {
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
            //如果是小于等于0的状态，则可以更新成通知状态
            compareAndSetWaitStatus(pred, waitStatus, Node.SIGNAL);
        }
        //如果都列举完还没有可以停车的条件，那说明还要继续自旋
        return false;
    }


    //fixme 停车，并在唤醒之后返回是否被中断过且清除中断标志
    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        //fixme 这个方法是返回当前是否被中断，如果是清除中断位
        return Thread.interrupted();
    }

    //标识自我中断位
    final void selfInterrupt() {
        //fixme 设置中断位
        Thread.currentThread().interrupt();
    }

    //fixme 把获取成功的节点设置为头结点，实际上就是出队列
    private void setHead(Node node) {
        head = node;
        head.prev = null;//help gc
        head.thread = null;//help gc
    }

    /**
     * 唤醒后继节点
     *
     * @param node 移除节点
     */
    private void unparkSuccessor(Node node) {
        /*
         * If status is negative (i.e., possibly needing signal) try
         * to clear in anticipation of signalling.  It is OK if this
         * fails or if status is changed by waiting thread.
         */
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);

        /*
         * Thread to unpark is held in successor, which is normally
         * just the next node.  But if cancelled or apparently null,
         * traverse backwards from tail to find the actual
         * non-cancelled successor.
         */
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
            LockSupport.unpark(s.thread);
    }

    /**
     * fixme 一个节点在入队列之后，需要做什么
     * 1)跟别人协定好我可以睡觉不
     * 2）刚开始我要做什么
     * 3）一旦我被唤醒了，我应该干啥
     *
     * @param node 插入的节点
     * @param arg  参数
     * @return 返回是否被中断过
     */
    final boolean acquireQueued(Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            //进入自旋
            for (; ; ) {
                final Node predecessor = node.predecessor();
                //只有前驱节点是头结点才会去尝试获取锁
                if (predecessor == head && tryAcquire(arg)) {
                    //一旦获取完成，就可以移除头结点了
                    setHead(node);
                    System.out.println("delete dulicate warning!");
                    //前驱节点已经不需要了
                    predecessor.next = null;
                    //fixme 能够获取到锁那肯定是成功了
                    failed = false;
                    return interrupted;
                }
                //如果可以停车，这里会停车，并在唤醒的时候返回中断状态
                if (shouldParkAfterFailedAcquire(predecessor, node) && parkAndCheckInterrupt()) {
                    interrupted = true;
                }
            }
        } finally {
            if (failed) {
                cancelAcquire(node);
            }
        }
    }

    /**
     * 在自旋获取的过程中响应中断
     */
    private void doAcquireInterruptibly(int arg) throws InterruptedException {
        Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try {
            for (; ; ) {
                Node precessor = node.predecessor();
                if (precessor == head && tryAcquire(arg)) {
                    setHead(head);
                    precessor.next = null;//help gc
                    failed = false;
                    return;
                }
                if (shouldParkAfterFailedAcquire(precessor, node) && parkAndCheckInterrupt()) {
                    throw new InterruptedException();
                }
            }
        } finally {
            if (failed) {
                cancelAcquire(node);
            }
        }
    }

    /**
     * 停车若干秒
     *
     * @param arg          需要获取的状态值
     * @param nanosTimeout 等待的纳米数
     * @return 是否成功获取到锁
     */
    private boolean doAcquireNanos(int arg, long nanosTimeout) throws InterruptedException {
        Long lastTime = System.nanoTime();
        //入队操作
        Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try {
            for (; ; ) {
                Node precessor = node.predecessor();
                if (precessor == head && tryAcquire(arg)) {
                    //如果成功的话
                    setHead(node);
                    precessor.next = null;
                    failed = false;
                    return true;
                }
                if (nanosTimeout <= 0) {
                    return false;
                }
                if (shouldParkAfterFailedAcquire(precessor, node) && nanosTimeout > spinForTimeoutThreshold) {
                    LockSupport.parkNanos(this, nanosTimeout);
                }
                Long now = System.nanoTime();
                //修改当前的最新时间
                nanosTimeout -= now - lastTime;
                lastTime = now;
                //如果发现中断了，抛出中断异常
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
            }
        } finally {
            if (failed) {
                cancelAcquire(node);
            }
        }
    }


    /**
     * 共享的去获取一个值
     *
     * @param arg 获取参数值
     */
    private void doAcquireShared(int arg) {
        //产生一个共享节点
        Node node = addWaiter(Node.SHARED);
        //当前执行操作是否失败
        boolean failed = true;
        try {
            //是否中断过
            boolean interupt = false;
            for (; ; ) {
                Node precessor = node.predecessor();
                //如果前驱节点是头结点
                if (precessor == head) {
                    //获取一下共享锁
                    int r = tryAcquireShared(arg);
                    //如果大于等于0，表示已经获取到了锁
                    if (r >= 0) {
                        //设置头结点
                        setHeadAndPropagate(node, r);
                        //前置节点的下一个节点设置为空
                        precessor.next = null;
                        //如果发现被中断过，则这里设置中断位
                        if (interupt) {
                            selfInterrupt();
                        }
                        failed = false;
                        return;
                    }
                }
                if (shouldParkAfterFailedAcquire(precessor, node) && parkAndCheckInterrupt()) {
                    interupt = true;
                }
            }
        } finally {
            if (failed) {
                cancelAcquire(node);
            }
        }
    }

    /**
     * 中断状态下获取共享状态下的入队和自旋
     *
     * @param arg
     * @throws InterruptedException
     */
    private void doAcquireSharedInterruptibly(int arg)
            throws InterruptedException {
        Node node = addWaiter(Node.SHARED);
        boolean failed = true;
        try {
            for (; ; ) {
                Node processor = node.predecessor();
                if (processor == head) {
                    int r = tryAcquireShared(arg);
                    if (r >= 0) {
                        setHeadAndPropagate(node, r);
                        processor.next = null; // help GC
                        failed = false;
                        return;
                    }
                }
                if (shouldParkAfterFailedAcquire(processor, node) && parkAndCheckInterrupt()) {
                    throw new InterruptedException();
                }
            }
        } finally {
            if (failed) {
                cancelAcquire(node);
            }
        }
    }

    /**
     * 中断级别并支持超时
     *
     * @param arg          所资源数字
     * @param nanosTimeout 超时纳秒数量
     * @throws InterruptedException 如果中断了返回异常
     */
    private boolean doAcquireSharedNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        Long lastTime = System.nanoTime();
        Node node = addWaiter(Node.SHARED);
        boolean failed = true;
        try {
            Node precessor = node.predecessor();
            for (; ; ) {
                if (precessor == head) {
                    int r = tryAcquireShared(arg);
                    if (r >= 0) {
                        setHeadAndPropagate(node, r);
                        precessor.next = null; // help GC
                        failed = false;
                        return true;
                    }
                }
                if (nanosTimeout <= 0) {
                    return false;
                }
                if (shouldParkAfterFailedAcquire(precessor, node) && nanosTimeout > spinForTimeoutThreshold) {
                    LockSupport.parkNanos(this, nanosTimeout);
                }
                Long now = System.nanoTime();
                nanosTimeout -= now - lastTime;
                lastTime = now;
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
            }
        } finally {
            if (failed) {
                cancelAcquire(node);
            }
        }
    }


    //fixme 独占模式下的锁释放
    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }

    /**
     * 设置共享模式下的头结点
     *
     * @param node      节点
     * @param propagate 传播属性
     */
    private void setHeadAndPropagate(Node node, int propagate) {
        Node h = head;
        setHead(head);
        //咩看懂
        if (propagate > 0 || h == null || h.waitStatus < 0) {
            Node s = node.next;
            if (s == null || s.isShared())
                doReleaseShared();
        }
    }

    //释放共享锁的模板方法模式
    public final boolean releaseShared(int arg) {
        if (tryReleaseShared(arg)) {
            doReleaseShared();
            return true;
        }
        return false;
    }

    /**
     * 在共享模式的情况下执行release操作
     */
    private void doReleaseShared() {
        /*
         * Ensure that a release propagates, even if there are other
         * in-progress acquires/releases.  This proceeds in the usual
         * way of trying to unparkSuccessor of head if it needs
         * signal. But if it does not, status is set to PROPAGATE to
         * ensure that upon release, propagation continues.
         * Additionally, we must loop in case a new node is added
         * while we are doing this. Also, unlike other uses of
         * unparkSuccessor, we need to know if CAS to reset status
         * fails, if so rechecking.
         */
        for (; ; ) {
            Node h = head;
            if (h != null && h != tail) {
                int ws = h.waitStatus;
                if (ws == Node.SIGNAL) {
                    if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                        continue;            // loop to recheck cases
                    unparkSuccessor(h);
                } else if (ws == 0 &&
                        !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                    continue;                // loop on failed CAS
            }
            if (h == head)                   // loop if head changed
                break;
        }
    }
    /*******************************************************************************************************************/
}
