package netty.selector.zerocopy;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 传统JavaIO的客户端
 */
public class OldClient {
    public static void main(String[] args) throws Exception{

        //准备与服务器的连接
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",8000));
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        //准备要传输的文件
        //InputStream inputStream = OldClient.class.getResourceAsStream("./test.zip");
        File file = new File("test.zip");
        FileInputStream inputStream = new FileInputStream(file);

        byte[] bytes = new byte[4096];
        int readcount;
        int total=0;

        long timeMillis = System.currentTimeMillis();
        //发送文件
        while((readcount = inputStream.read(bytes))>=0){

            dataOutputStream.write(bytes,0,readcount);
            total += readcount;
        }

        System.out.println("一共发送了"+total+"个字节的数据，耗时"+(System.currentTimeMillis()-timeMillis));

        inputStream.close();
        dataOutputStream.close();
        socket.close();

    }
}
