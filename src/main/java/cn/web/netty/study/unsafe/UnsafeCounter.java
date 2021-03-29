package cn.web.netty.study.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author : wangebie
 * @date : 2021/3/29 16:44
 */
public class UnsafeCounter implements Counter {
    private volatile int count;
    private static final long countOffset;
    private static final Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            countOffset = unsafe.objectFieldOffset(UnsafeCounter.class.getDeclaredField("count"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public void increase() {
        while (true) {
            int x = count;
            if (x >= 10) break;
            boolean cas = cas(x, x + 1);
            if (cas) System.out.println(Thread.currentThread().getName() + " : " + (x + 1));
        }


    }

    private final boolean cas(int except, int update) {
        return unsafe.compareAndSwapInt(this, countOffset, except, update);
    }
}
