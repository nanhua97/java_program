package netty.netty.nettyChat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 群聊服务端处理类
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    //定义一个列表用来存储所有与服务端保持连接的channel
    //GlobalEventExecutor.INSTANCE是一个全局的事件执行器,一个单例对象
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 通道加入
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【客户端】"+ctx.channel().remoteAddress()+"加入聊天"+sdf.format(new Date()));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【客户端】"+ctx.channel().remoteAddress()+"离开了\n");
    }

    /**
     * 通道活动时触发此方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.add(channel);
        //channelGroup提供了向本列表中所有channel发送消息的方法
        channelGroup.writeAndFlush("用户"+channel.remoteAddress()+"上线了");
        System.out.println("用户"+channel.remoteAddress()+"上线了");
    }

    /**
     * channel处于不活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.remove(channel);
        channelGroup.writeAndFlush("用户"+channel.remoteAddress()+"下线了");
        System.out.println("用户"+channel.remoteAddress()+"下线了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch ->{
            if (ch != channel){
                ch.writeAndFlush("【客户】"+channel.remoteAddress()+":" + msg +"\n");
            }else {
                ch.writeAndFlush("【自己】发送了消息"+msg + "\n");
            }
        });

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        System.out.println(cause.getMessage());
    }
}
