package com.baizhi.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//创建客户端对象
public class TestSocket {

    public static void main(String[] args) throws IOException {

        //创建socket对象
        Socket socket = new Socket("192.168.43.81", 8989);

        //向服务器发送数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello Server".getBytes());
        //代表客户端发送的数据完成
        socket.shutdownOutput();

        //获取服务器端响应数据
        InputStream inputStream = socket.getInputStream();
        int len = 0;
        byte[] b = new byte[1024];
        StringBuilder builder = new StringBuilder();
        while(true){
            len = inputStream.read(b);
            if(len==-1) {break;}
            builder.append(new String(b,0,len));
        }
        System.out.println(builder.toString());

        socket.shutdownInput();//确定读取响应数据结束


    }

}

