package com.baizhi.servers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//创建服务器端
public class TestServerSocket002 {


    //操作   进程   开启多线程  进程数量有限的    系统上线 cpu  1  100000000   1
     public static void main(String[] args) throws IOException, InterruptedException {

         //创建线程池
         ExecutorService executorService = Executors.newFixedThreadPool(10);
        //创建serverSocket对象
        ServerSocket serverSocket = new ServerSocket(8989);
        System.out.println("服务器已经启动......");
        while (true){
            //接收客户端请求  每一个请求创建一个线程
           final Socket socket = serverSocket.accept();
               executorService.submit(new Runnable() {
                   public void run() {
                       try {
                           //处理请求数据
                           InputStream inputStream = socket.getInputStream();
                           //封装过滤流
                           DataInputStream dataInputStream = new DataInputStream(inputStream);
                           String readUTF = dataInputStream.readUTF();
                           System.out.println(readUTF +" 线程名称:"+Thread.currentThread().getName());
                           socket.shutdownInput();//明确处理请求数据完毕
                           //处理业务
                           if(readUTF.equals("abc")){
                               Thread.sleep(10000);
                           }
                           //响应客户端对象
                           OutputStream outputStream = socket.getOutputStream();
                           //封装过滤流
                           DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                           dataOutputStream.writeUTF("我是server,我已经将你发送的请求处理完毕,没事别来找我.....");
                           socket.shutdownOutput();//明确服务器响应完毕
                       } catch (IOException e) {
                           e.printStackTrace();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               });




        }
    }
}
