package moe.cnkirito.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import moe.cnkirito.netty.model.AgentRequest;
import moe.cnkirito.netty.model.AgentResponse;
import moe.cnkirito.netty.model.AgentSerializationUtil;

public class AgentClientEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (in instanceof AgentRequest) {
            byte[] serialize = AgentSerializationUtil.serializeRequest((AgentRequest) in);
            out.writeInt(serialize.length);
            out.writeBytes(serialize);
        }
    }


}
