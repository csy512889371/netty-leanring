package moe.cnkirito.netty;

import moe.cnkirito.netty.server.AgentServerConnecManager;

/**
 * @author 徐靖峰[OF2938]
 * company qianmi.com
 * Date 2018-05-17
 */
public class ServerApp {
    public static void main(String[] args) throws InterruptedException {
        new AgentServerConnecManager().initBootstrap();
//        new CountDownLatch(1).await();
    }
}
