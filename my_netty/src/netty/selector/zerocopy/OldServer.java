package netty.selector.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统JavaIO的服务器
 */
public class OldServer {
    public static void main(String[] args) throws Exception {
        //绑定本机的8000端口
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true){
            //接收请求
            Socket socket = serverSocket.accept();

            //获取输入流
            InputStream inputStream = socket.getInputStream();

            //DataInputStream继承了InputStream，同时增加了readInt()，readUTF()等方法，增加了类型判断，读取输入流时会根据对应类型的字节长度自动停止
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            //循环读取
            while (true){
                try {
                    byte[] bytes = new byte[4096];

                    int read = dataInputStream.read(bytes, 0, bytes.length);

                    if (-1 == read){
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
