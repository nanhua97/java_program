package netty.netty.nettyChat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 群聊服务端
 */
public class ChatServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(bossGroup,wokerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind("127.0.0.1", 8000).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (channelFuture.isSuccess()){
                        System.out.println("服务器端口绑定成功");
                    }else {
                        System.out.println("服务器端口绑定失败");
                    }
                }
            });

            ChannelFuture channelFuture1 = channelFuture.channel().closeFuture().sync();

        }catch (Exception e){

        }finally {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }

    }
}
