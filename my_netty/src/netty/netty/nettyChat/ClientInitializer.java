package netty.netty.nettyChat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ClientInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast(new ClientHandler());
    }
}
