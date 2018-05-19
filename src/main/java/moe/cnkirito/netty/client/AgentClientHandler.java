package moe.cnkirito.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import moe.cnkirito.netty.model.AgentFuture;
import moe.cnkirito.netty.model.AgentRequestHolder;
import moe.cnkirito.netty.model.AgentResponse;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AgentClientHandler extends SimpleChannelInboundHandler<AgentResponse> {
    private static AtomicLong beginTime = new AtomicLong(0);
    private static AtomicInteger totalRequest = new AtomicInteger(0);

    public static final Thread THREAD = new Thread(() -> {
        try {
            while (true) {
                long duration = System.currentTimeMillis() - beginTime.get();
                if (duration != 0) {
                    System.out.println("qps: " + 1000 * totalRequest.get() / duration);
                    Thread.sleep(2000);
                }
            }

        } catch (InterruptedException ignored) {
        }
    });

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AgentResponse response) {
        long requestId = response.getId();
        AgentFuture future = AgentRequestHolder.get(requestId);
        if (null != future) {
            AgentRequestHolder.remove(requestId);
            future.done(response);

            totalRequest.incrementAndGet();

            if (beginTime.compareAndSet(0, System.currentTimeMillis())) {
                THREAD.start();
            }

        }
    }
}
