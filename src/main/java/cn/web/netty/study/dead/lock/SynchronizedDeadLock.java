package cn.web.netty.study.dead.lock;

/**
 * @author : wangebie
 * @date : 2021/3/30 16:38
 */
public class SynchronizedDeadLock extends Thread {
    private final String first;
    private final String second;

    public SynchronizedDeadLock(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String lockA = "lockA", lockB = "lockB";
        new SynchronizedDeadLock("Thread1", lockA, lockB).start();
        new SynchronizedDeadLock("Thread2", lockB, lockA).start();


    }
}
