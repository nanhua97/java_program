package netty.netty.simple;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientInHandler extends ChannelInboundHandlerAdapter {
    /**
     * 通道就绪就会触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("客户端的ChannelHandlerContext = " + ctx);

        ctx.writeAndFlush(Unpooled.copiedBuffer("这是客户端ChannelInboundHandler的channelActive方法", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("========================================================");
        System.out.println("服务端返回消息:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("========================================================");
    }

    /**
     * 出现异常时会触发
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.channel().close();
    }
}
