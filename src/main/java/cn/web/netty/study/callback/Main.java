package cn.web.netty.study.callback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author : wangebie
 * @date : 2021/4/16 10:43
 */
public class Main {
    public static void main(String[] args) {
        //计数器，防止任务还没执行完，主线程就结束了
        CountDownLatch cdl = new CountDownLatch(1);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : task");
            cdl.countDown();
            return "succeed";
        });
        future.whenComplete((s, throwable) -> {
            System.out.println(s);
            System.out.println(Thread.currentThread().getName()+" : invoker");
        });
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
