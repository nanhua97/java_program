package netty.selector.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 使用NIO零拷贝的服务器
 */
public class ZeroCopyServer {
    public static void main(String[] args) throws Exception {
        //开启服务器
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号
        serverSocketChannel.bind(new InetSocketAddress("localhost",8000));
        System.out.println("服务器开启");

        ByteBuffer buffer = ByteBuffer.allocate(4096);


        while(true){

            //接收请求
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("接受到来自"+socketChannel.getRemoteAddress()+"的请求");

            int readCount = 0;

            while (-1 != readCount){
                readCount = socketChannel.read(buffer);
                //重置buffer,将buffer的写入位置重置为开始
                buffer.rewind();
            }

            System.out.println("读取完毕");
        }

    }
}
