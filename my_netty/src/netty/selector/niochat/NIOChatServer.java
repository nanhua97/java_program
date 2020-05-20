package netty.selector.niochat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO网络编程--多人聊天室(服务端)
 */
public class NIOChatServer {

    private final String HOST = "127.0.0.1";

    private final int PORT = 8888;

    private Selector selector = null;

    private ServerSocketChannel serverChannel = null;

    public NIOChatServer() {
        try {
            //新建selector
            this.selector = Selector.open();

            //建立服务端通道
            this.serverChannel = ServerSocketChannel.open();

            //绑定监听地址
            this.serverChannel.bind(new InetSocketAddress(HOST,PORT));

            //设置为非阻塞模式
            this.serverChannel.configureBlocking(false);

            //将Channel注册到selector上
            this.serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
    //监听
    public void listen() {
        try {

            while (true){
                //未监听到事件
                if (selector.select(3000)>0){

                    //从selector中得到所有发生事件的的SelectionKey
                    Set<SelectionKey> clientKeys = selector.selectedKeys();

                    //迭代器
                    Iterator<SelectionKey> keys = clientKeys.iterator();

                    //循环遍历所有事件Key
                    while (keys.hasNext()){

                        SelectionKey clientKey = keys.next();

                        //如果为连接事件
                        if (clientKey.isAcceptable()){

                            //接受连接
                            SocketChannel clientChannel = serverChannel.accept();

                            //设置为非阻塞
                            clientChannel.configureBlocking(false);

                            //注册到Selector,监听读事件
                            clientChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));

                            String msg = clientChannel.getRemoteAddress() + "上线了";

                            System.out.println(msg);

                            sendData(msg,clientChannel);

                        }else if (clientKey.isReadable()){

                            readData(clientKey);

                        }

                        //移除处理过的Key
                        keys.remove();
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取数据
    public void readData(SelectionKey clientKey){
        SocketChannel clientChannel = null;
        try {
            //如果监听到读事件
            clientChannel = (SocketChannel)clientKey.channel();
            //获取到绑定的buffer
            ByteBuffer clientBuffer = (ByteBuffer) clientKey.attachment();
            //读取数据到buffer
            clientChannel.read(clientBuffer);

            System.out.println(new String(clientBuffer.array(),0,clientBuffer.position()));

            //向别的客户端发送消息
            sendData(clientChannel.getRemoteAddress()+":"+new String(clientBuffer.array(),0,clientBuffer.position()),clientChannel);
        } catch (IOException e) {

            try {
                System.out.println(clientChannel.getRemoteAddress()+"离线了...");
                //发送数据
                sendData(clientChannel.getRemoteAddress()+"离线了...",clientChannel);
                //离线后关闭key
                clientKey.cancel();
                //离线后关闭channel
                clientChannel.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
    //发送数据
    public void sendData(String msg, SocketChannel clientChannel){

        try {
            Set<SelectionKey> clientKeys = selector.keys();

            for (SelectionKey key : clientKeys) {

                Channel channel = key.channel();

                if (channel instanceof SocketChannel && channel != clientChannel){

                    SocketChannel channel1 = (SocketChannel) channel;

                    channel1.write(ByteBuffer.wrap(msg.getBytes()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NIOChatServer nioChatServer = new NIOChatServer();
        nioChatServer.listen();
    }

}
