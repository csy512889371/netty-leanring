package moe.cnkirito.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import moe.cnkirito.netty.client.AgentClientInitializer;

public class AgentServerConnecManager {
    EventLoopGroup bossGroup = new NioEventLoopGroup(4);
    EventLoopGroup workerGroup = new NioEventLoopGroup(4);

    private ServerBootstrap bootstrap;

    public void initBootstrap() {
        try{

        bootstrap = new ServerBootstrap()
                .group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new AgentServerInitializer())
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);

            ChannelFuture future = bootstrap.bind("127.0.0.1", 9111);
            future.syncUninterruptibly();
        }catch (Exception e){
            e.printStackTrace();
        }
//        finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//        }
    }
}
