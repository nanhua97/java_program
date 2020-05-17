package netty.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 实现ScatteringByteChannel接口的通道可以按序将通道中的数据读到Buffer数组中
 * 实现GatheringByteChannel接口的通道可以按序将通道中的数据写到Buffer数组中
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {

        //创建服务端socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //绑定地址与端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",7000);

        //绑定地址与端口
        serverSocketChannel.bind(inetSocketAddress);


        ByteBuffer[] byteBuffers = new ByteBuffer[2];

        //第一个buffer
        byteBuffers[0] = ByteBuffer.allocate(5);

        //第二个buffer
        byteBuffers[1] = ByteBuffer.allocate(5);

        //接受请求
        SocketChannel accept = serverSocketChannel.accept();

        //爱着你之每次读取的字节长度
        int messageLength = 10;

        //循环读取
        while(true){

            int byteRead = 0;
            //读满这个buffer数组后再进行处理
            while(byteRead<messageLength){

                //实现了ScatteringByteChannel的read方法,会将读取到的数据按序写入Buffer数组中
                long read = accept.read(byteBuffers);

                byteRead += read;

                System.out.println("byteRead:"+byteRead);

                //查看当前buffer的position和limit
                Arrays.asList(byteBuffers).stream().map(buffer->{
                    return "position:"+buffer.position()+"\tlimit:"+buffer.limit();
                }).forEach(System.out::println);

                Arrays.asList(byteBuffers).stream().map(buffer->"buffer数据:"+new String(buffer.array(),0,buffer.position())).forEach(System.out::println);

                //反转Buffer操作
                Arrays.asList(byteBuffers).stream().forEach(ByteBuffer::flip);

                //写入Buffer操作
                int byteWrite = 0;

                while (byteWrite<messageLength){
                    //实现了GatheringByteChannel通道
                    long write = accept.write(byteBuffers);

                    byteWrite += write;

                }

                Arrays.asList(byteBuffers).stream().forEach(ByteBuffer::clear);

            }


        }

    }
}
