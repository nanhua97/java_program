package netty.buffer;

import java.nio.IntBuffer;

/**
 * 初识Buffer
 */
public class BufferDemo {
    public static void main(String[] args) {
        //Buffer的简单使用
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //写入buffer操作
        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.put(3);
        intBuffer.put(4);
        intBuffer.put(5);

        //反转操作  写 --> 读
        intBuffer.flip();

        //读
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
