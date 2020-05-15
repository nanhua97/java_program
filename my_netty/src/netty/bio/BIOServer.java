package netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //如果有请求过来就新建一个线程去处理
        ServerSocket serverSocket = new ServerSocket(9999);

        while (true){
            //监听来自客户端的链接
            final Socket accept = serverSocket.accept();

            //创建一个线程与之通信
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程:"+Thread.currentThread().getName());
                    handler(accept);
                }
            });

        }

    }
    public static void handler(Socket socket){

        try {
            InputStream inputStream = socket.getInputStream();

            byte[] bytes = new byte[1024];

            while (true){
                int read = inputStream.read(bytes);

                if (read!=-1){
                    System.out.println("客户端发送的消息:"+new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭client连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
