package netty.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义一个Handler(需要Netty定义好的某个HandlerAdapter)
 */
public class NettyServerInHandler extends ChannelInboundHandlerAdapter {

    /**
     * 用作读取数据
     * @param ctx   上下文对象,含有管道pipeline,通道channel等信息
     * @param msg   接收到的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //System.out.println("server ChannelHandlerContext = " + ctx);

        //ByteBuf 是Netty的类
        ByteBuf buf = (ByteBuf) msg;

        System.out.println("========================================================");
        System.out.println("客户端发送消息:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:" + ctx.channel().remoteAddress());
        System.out.println("========================================================");
    }

    /**
     * 读取数据完毕触发的方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        //System.out.println("读取消息完毕");
        //将数据进行编码
        ByteBuf bytebuf = Unpooled.copiedBuffer("这是ChannelInboundHandler的channelReadComplete方法", CharsetUtil.UTF_8);
        //将数据写入到缓存并刷新
        ctx.writeAndFlush(bytebuf);

    }

    /**
     * 发生异常是触发的方法
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
