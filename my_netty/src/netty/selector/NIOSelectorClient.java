package netty.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * selector客户端
 */
public class NIOSelectorClient {
    public static void main(String[] args) throws IOException {

        SocketChannel clientChannel = SocketChannel.open();

        if (!clientChannel.connect(new InetSocketAddress("127.0.0.1",8888))){
            while (!clientChannel.finishConnect()){
                System.out.println("正在链接......");
            }
        }

        clientChannel.write(ByteBuffer.wrap("你好".getBytes()));

        System.in.read();
    }
}
