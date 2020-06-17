package netty.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyTcpServer {
    public static void main(String[] args) {

        //建立boss(负责接受请求)与woker(处理请求)的线程组
        //默认为cpu核数*2
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)        //设置使用服务端使用的Channel类型为NioServerSocketChannel
                    .option(ChannelOption.SO_BACKLOG,128)       //设置父线程组最大连接数为128
                    .childOption(ChannelOption.SO_KEEPALIVE,true)       //设置子线程组的通道状态为保持连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //为子线程组添加自定义Handler
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(null);
                        }

                    });
            ChannelFuture channelFuture = serverBootstrap.bind("localhost", 8000).sync();

            ChannelFuture sync = channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
