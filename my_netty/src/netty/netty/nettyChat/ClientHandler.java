package netty.netty.nettyChat;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

public class ClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes()));
        }*/
        ctx.writeAndFlush(Unpooled.copiedBuffer("123123".getBytes()));

    }
}
