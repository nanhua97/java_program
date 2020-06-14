package netty.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通过Channel与Buffer写入数据到文件
 */
public class NIOFileChannelWrite {
    public static void main(String[] args) throws IOException {
        //建立文件对象
        File file = new File("C:\\Users\\SAFX\\Desktop\\1.txt");

        //建立输出流对象
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        //获取通道
        FileChannel channel = fileOutputStream.getChannel();

        //建立Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        String s = "hello world!";

        //将string转为字节写入buffer
        byteBuffer.put(s.getBytes());

        //反转buffer操作
        byteBuffer.flip();

        //将buffer中的内容写入channel
        channel.write(byteBuffer);

        channel.close();

        fileOutputStream.close();
    }
}
