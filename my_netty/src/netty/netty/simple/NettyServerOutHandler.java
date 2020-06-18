package netty.netty.simple;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

public class NettyServerOutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        String message = (String) msg;
        message = "来自ChannelOutboundHandler的信息添加\n" + message;
        ctx.writeAndFlush(Unpooled.copiedBuffer(message, CharsetUtil.UTF_8));

    }
}
