package cn.web.netty.study.dead.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangebie
 * @date : 2021/3/30 16:27
 */
public class ReentrantDeadLock extends Thread {

    private ReentrantLock lock1;
    private ReentrantLock lock2;

    public ReentrantDeadLock(ReentrantLock lock1, ReentrantLock lock2) {
        super();
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        lock1.lock();
        System.out.println(Thread.currentThread().getName() + " 获取到了" + lock1.toString());
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + " is trying to get lock2 ："+lock2.toString());
            lock2.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
            lock2.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock(), lock2 = new ReentrantLock();
        new ReentrantDeadLock(lock1, lock2).start();
        new ReentrantDeadLock(lock2, lock1).start();
    }
}
