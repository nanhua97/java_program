package netty.netty.taskQueue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
   /* @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("response,"+ctx.channel().remoteAddress(),CharsetUtil.UTF_8));
    }*/

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println("=====================");
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("接受到消息:"+byteBuf.toString(CharsetUtil.UTF_8));

        //耗时操作
        //Thread.sleep(1000*10);

        //channel下的eventloop中分有一个线程用作TaskQueue,可用作异步操作
        try {
            ctx.channel().eventLoop().execute(()->{
                try {
                    Thread.sleep(1000*10);
                    System.out.println("异步操作执行完毕......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ctx.channel().eventLoop().execute(()->{
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("异步操作执行完毕......");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=====================");



    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("消息读取完毕");

        ByteBuf byteBuf = Unpooled.copiedBuffer("response", CharsetUtil.UTF_8);

        ctx.writeAndFlush(byteBuf);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println(cause.getMessage());

        ctx.channel().close();

    }
}
