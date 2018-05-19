package moe.cnkirito.netty;

import moe.cnkirito.netty.client.AgentClient;

/**
 * @author 徐靖峰[OF2938]
 * company qianmi.com
 * Date 2018-05-17
 */
public class ClientApp {

    public static void main(String[] args) {
        AgentClient agentClient = new AgentClient();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        agentClient.invoke("1", "1", "1", "1");
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }).start();

        }
    }

}
