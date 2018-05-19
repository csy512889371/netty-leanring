package moe.cnkirito.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import moe.cnkirito.netty.model.AgentRequest;
import moe.cnkirito.netty.model.AgentResponse;
import moe.cnkirito.netty.model.AgentSerializationUtil;

public class AgentServerEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if(in instanceof AgentResponse){
            byte[] serialize = AgentSerializationUtil.serializeResponse((AgentResponse) in);
            out.writeInt(serialize.length);
            out.writeBytes(serialize);
        }
    }


}
