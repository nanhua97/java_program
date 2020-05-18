package netty.selector.niochat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOChatClient {
    private String HOST = "127.0.0.1";
    private int PORT = 8888;
    private SocketChannel clientChannel = null;

    public NIOChatClient() {

        try {
            clientChannel = SocketChannel.open(new InetSocketAddress(HOST,PORT));

            Scanner scanner = new Scanner(System.in);

            String msg = scanner.nextLine();

            clientChannel.write(ByteBuffer.wrap(msg.getBytes()));

            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NIOChatClient nioChatClient = new NIOChatClient();
    }
}
