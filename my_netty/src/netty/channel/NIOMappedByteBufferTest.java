package netty.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer 可以让文件直接在堆外内存修改,操作系统不需要拷贝一次
 */
public class NIOMappedByteBufferTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\SAFX\\Desktop\\1.txt", "rw");

        FileChannel channel = randomAccessFile.getChannel();

        /**
         * 参数一:文件通道的读写模式
         * 参数二:文件通道的起始位置
         * 参数三:文件通道的最后位置(修改不能超过此Buffer的限制),映射到内存的大小
         * 实际类型为DirectByteBuffer(MappedByteBuffer的实现类)
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        map.put(0, (byte) 'S');

        map.put(3, (byte) 'K');

        channel.close();

    }
}
