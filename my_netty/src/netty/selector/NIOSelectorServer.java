package netty.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用selector技术的非阻塞服务端
 */
public class NIOSelectorServer {
    public static void main(String[] args) throws IOException {

        //新建一个Selector
        Selector selector = Selector.open();

        //在服务端新建一个通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        //服务端通道绑定端口
        serverChannel.bind(new InetSocketAddress(8888));

        //设置通道模式为非阻塞模式
        serverChannel.configureBlocking(false);

        System.out.println("服务器启动");

        //将通道注册到selector上,同时指明此select监听的事件
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);


        while(true){
            if (selector.select(2000)==0){
                System.out.println("等待2秒,无连接...");
                continue;
            }

            //获得发生事件的selectionKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //变为可迭代对象
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            //处理发生事件的通道
            while(iterator.hasNext()){

                SelectionKey clientKey = iterator.next();

                //如果发生的事件为连接事件
                if (clientKey.isAcceptable()){

                    //接受客户端的请求并生成通道
                    SocketChannel clientChannel = serverChannel.accept();

                    clientChannel.configureBlocking(false);

                    //将为客户端生成的通道注册到selector,指定监听事件为读时间,关联一个Buffer
                    clientChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    System.out.println("客户端:"+clientChannel.getRemoteAddress()+"连接成功");

                }else if (clientKey.isReadable()){

                    //如果select监听到客户端的可读事件,根据selectionKey获取到发生事件的通道
                    SocketChannel clientChannel = (SocketChannel) clientKey.channel();

                    //获取该Channel并定的Buffer
                    ByteBuffer clientBuffer = (ByteBuffer)clientKey.attachment();

                    clientChannel.read(clientBuffer);

                    System.out.println("客户端发送的数据是:"+new String(clientBuffer.array(),0,clientBuffer.position()));

                    clientBuffer.clear();
                }

                System.out.println(selector.keys());

                iterator.remove();
                //将处理过的key从列表中删除
                //clientKey.cancel();

                System.out.println(selector.keys());
            }
        }

    }
}
