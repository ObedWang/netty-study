package cn.web.netty.study.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author : wangebie
 * @date : 2021/3/29 15:38
 */
public class Main {


    public static void main(String[] args) {
        Counter counter = new NormalCounter();
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(counter)).start();
            Thread.yield();
        }
    }

    public static final class Task implements Runnable {
        Counter counter;

        public Task(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.increase();
        }
    }

}
