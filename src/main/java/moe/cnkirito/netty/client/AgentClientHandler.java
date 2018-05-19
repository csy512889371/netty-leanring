package moe.cnkirito.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import moe.cnkirito.netty.model.AgentRequestHolder;
import moe.cnkirito.netty.model.AgentFuture;
import moe.cnkirito.netty.model.AgentResponse;

public class AgentClientHandler extends SimpleChannelInboundHandler<AgentResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AgentResponse response) {
        long requestId = response.getId();
        AgentFuture future = AgentRequestHolder.get(requestId);
        if(null != future){
            AgentRequestHolder.remove(requestId);
            future.done(response);
        }
    }
}
