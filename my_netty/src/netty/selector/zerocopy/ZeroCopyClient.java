package netty.selector.zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 使用NIO零拷贝的客户端
 * 将文件直接从系统内的Buffer传递到了SocketChannel的buffer
 * 免去了传给用户Buffer的操作
 */
public class ZeroCopyClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost",8000));

        File file = new File("test.zip");

        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel channel = fileInputStream.getChannel();

        long timeMillis = System.currentTimeMillis();

        //windows下transferTo有8M的限制
        channel.transferTo(0,channel.size(),socketChannel);

        System.out.println("一共发送了"+channel.size()+"个字节的数据，耗时"+(System.currentTimeMillis()-timeMillis));

        channel.close();
        fileInputStream.close();
        socketChannel.close();

    }
}
