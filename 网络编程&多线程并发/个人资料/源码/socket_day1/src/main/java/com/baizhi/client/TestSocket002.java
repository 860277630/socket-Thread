package com.baizhi.client;

import java.io.*;
import java.net.Socket;

//创建一个客户端对象
public class TestSocket002 {

    public static void main(String[] args) throws IOException {


        for (int i = 0; i <100 ; i++) {
            //创建客户端对象
            Socket socket = new Socket("192.168.0.3",8989);
            //发送数据
            OutputStream outputStream = socket.getOutputStream();
            //封装过滤流
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("aaa");
            dataOutputStream.flush();
            socket.shutdownOutput();//请求数据发送完成


            //处理响应结果
            InputStream inputStream = socket.getInputStream();
            //封装过滤流
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println(dataInputStream.readUTF());
            socket.shutdownInput();//明确client处理响应完毕
        }


    }

}
