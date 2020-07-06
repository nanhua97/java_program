package netty.netty.nettyChat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("===============================");
        System.out.println(ctx.channel().remoteAddress()+":");
        System.out.println(msg);
        System.out.println("===============================");
    }
}
