package netty.netty.nettyChat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8000).sync();

            Channel channel = channelFuture.channel();

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                channel.writeAndFlush(line + "\r\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            loopGroup.shutdownGracefully();
        }


    }
}
