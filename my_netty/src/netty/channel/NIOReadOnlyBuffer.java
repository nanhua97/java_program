package netty.channel;

import java.nio.IntBuffer;

/**
 * 可设置ReadOnlyBuffer,只支持读操作的Buffer
 * ReadOnlyBuffer如果使用put操作会导致ReadOnlyBufferException
 */
public class NIOReadOnlyBuffer {
    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(64);

        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.put(3);
        intBuffer.put(4);
        intBuffer.put(5);

        intBuffer.flip();

        //设置为只读Buffer
        IntBuffer readOnlyBuffer = intBuffer.asReadOnlyBuffer();

        System.out.println(readOnlyBuffer.getClass());

        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.flip();

        //readOnlyBuffer.put(1);

    }
}
