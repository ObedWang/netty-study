package cn.web.netty.study.aio;

/**
 * @author : wangebie
 * @date : 2021/3/22 20:19
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {
            }
        }
        AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(timeServerHandler, "AIO-AsyncTimeServerHandler-001").start();
    }
}
