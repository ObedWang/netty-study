package cn.web.netty.study.unsafe;

/**
 * @author : wangebie
 * @date : 2021/3/29 16:54
 */
public class NormalCounter implements Counter {
    private int count;

    @Override
    public void increase() {
        while (true) {
            int x = count;
            if (x >= 10) break;
            System.out.println(Thread.currentThread().getName() + ": " + ++x);
            count=x;
        }
    }
}
