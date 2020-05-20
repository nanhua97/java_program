package netty.selector.niochat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * NIO网络编程--多人聊天室(客户端)
 */
public class NIOChatClient {
    private Selector selector;
    private String HOST = "127.0.0.1";
    private int PORT = 8888;
    private SocketChannel clientChannel = null;

    public NIOChatClient() {

        try {
            selector = Selector.open();

            clientChannel = SocketChannel.open(new InetSocketAddress(HOST,PORT));

            clientChannel.configureBlocking(false);

            clientChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(){
        try{
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            if (selector.select(2000)>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();

                    SocketChannel channel = (SocketChannel)next.channel();

                    channel.read(byteBuffer);

                    System.out.println(new String(byteBuffer.array(),0,byteBuffer.position()));

                    byteBuffer.clear();
                }
                iterator.remove();

            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendData(String info){
        try{
            clientChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NIOChatClient nioChatClient = new NIOChatClient();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    nioChatClient.readData();
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            nioChatClient.sendData(scanner.nextLine());
        }
    }
}
