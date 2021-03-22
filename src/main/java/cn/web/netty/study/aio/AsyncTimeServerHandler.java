package cn.web.netty.study.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author : wangebie
 * @date : 2021/3/22 20:19
 */
public class AsyncTimeServerHandler implements Runnable {
    int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void doAccept(){
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}
