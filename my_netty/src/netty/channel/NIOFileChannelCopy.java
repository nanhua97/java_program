package netty.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通过Channel与Buffer复制/拷贝文件
 * 通过Channel的transform方法复制文件
 */
public class NIOFileChannelCopy {
    public static void main(String[] args) throws IOException {
        transform();
    }

    public static void copyFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\SAFX\\Desktop\\1.txt");
        FileChannel channelA = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\SAFX\\Desktop\\2.txt");
        FileChannel channelB = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while(true){
            //清空buffer
            byteBuffer.clear();
            int write = channelA.read(byteBuffer);

            if (write != -1){
                byteBuffer.flip();
                channelB.write(byteBuffer);
            }else {
                break;
            }
        }

        channelA.close();
        channelB.close();
        fileInputStream.close();
        fileOutputStream.close();

    }

    public static void createFile() throws IOException {
        //新建文件
        File file = new File("C:\\Users\\SAFX\\Desktop\\1.txt");

        //转为输入流
        FileInputStream fileInputStream = new FileInputStream(file);

        //获取通道
        FileChannel channel = fileInputStream.getChannel();

        //建立Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //读取数据到buffer
        channel.read(byteBuffer);

        //转换读写
        byteBuffer.flip();

        //新建文件
        File file1 = new File("C:\\Users\\SAFX\\Desktop\\2.txt");

        if (!file1.exists()){
            file1.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file1);

        FileChannel channel1 = fileOutputStream.getChannel();

        channel1.write(byteBuffer);

        channel.close();
        channel1.close();

        fileOutputStream.close();
        fileInputStream.close();
    }

    public static void transform() throws IOException {

        File file = new File("1.jpg");

        System.out.println(file.length());

        FileInputStream fileInputStream = new FileInputStream("1.jpg");

        FileOutputStream fileOutputStream = new FileOutputStream("2.jpg");

        FileChannel inChannel = fileInputStream.getChannel();

        FileChannel outChannel = fileOutputStream.getChannel();

        inChannel.transferTo(0,file.length(),outChannel);

        inChannel.close();
        outChannel.close();

    }

}
