package netty.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通过Channel与Buffer读取文件
 */
public class NIOFileChannelRead {
    public static void main(String[] args) throws IOException {
        //新建文件
        File file = new File("C:\\Users\\SAFX\\Desktop\\1.txt");

        //新建输入流
        FileInputStream fileInputStream = new FileInputStream(file);

        //从输入流获取通道
        FileChannel channel = fileInputStream.getChannel();

        //建立Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //通道读取数据到buffer
        channel.read(byteBuffer);

        //将buffer中的数据转为字符串
        System.out.println(new String(byteBuffer.array()));

        channel.close();

        fileInputStream.close();

    }
}
