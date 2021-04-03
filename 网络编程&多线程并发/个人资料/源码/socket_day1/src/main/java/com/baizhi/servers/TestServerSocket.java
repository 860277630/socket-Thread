package com.baizhi.servers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建serverScoket对象  服务端对象
 */
public class TestServerSocket {

    public static void main(String[] args) throws IOException {
        //创建一个服务器
        ServerSocket serverSocket = new ServerSocket(8989);
        System.out.println("服务器已经启动........");
        while(true) {
            //让服务器接受|接收客户端
            Socket socket = serverSocket.accept();

            //处理请求数据
            InputStream inputStream = socket.getInputStream();
            StringBuilder builder = new StringBuilder();
            int len = 0;
            byte[] b = new byte[1024];
            while (true) {
                len = inputStream.read(b);
                if (len == -1) {break;}
                builder.append(new String(b, 0, len));
            }

            System.out.println(builder.toString());
            socket.shutdownInput();//获取请求数据 结束
            //处理业务
            //服务端响应客户端数据
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("讲".getBytes());
            socket.shutdownOutput();
        }
    }

}
