package netty.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class MyServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest){
            System.out.println(msg.getClass());
            HttpRequest request = (HttpRequest) msg;
            URI uri = new URI(request.uri());
            if ("/favicon.ico".equals(uri.getPath())){
                System.out.println("*******************");
                System.out.println("pipline hascode:"+ctx.pipeline().hashCode());
                System.out.println("Handler hascode:"+this.hashCode());
                System.out.println("*******************");

                System.out.println("无效的请求");
                return;
            }
            System.out.println(request.uri());
            System.out.println(request.headers());
            System.out.println("*******************");
            System.out.println("pipline hascode:"+ctx.pipeline().hashCode());
            System.out.println("Handler hascode:"+this.hashCode());
            System.out.println("*******************");
            ByteBuf byteBuf = Unpooled.copiedBuffer("netty服务器的响应", CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            response.headers().set(HttpHeaderNames.ACCEPT_CHARSET,"utf-8");
            ctx.writeAndFlush(response);


        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.channel().close();
    }
}
