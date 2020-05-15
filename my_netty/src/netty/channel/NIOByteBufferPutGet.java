package netty.channel;

import java.nio.ByteBuffer;

/**
 * Buffer中的数据,get时的类型需要与put时的类型一致,否则会出现BufferUnderflowException
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(1);
        byteBuffer.putChar('l');
        byteBuffer.putShort((short)1);

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getChar());
        //强转类型会出现BufferUnderflowException
        //System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getShort());
    }
}
