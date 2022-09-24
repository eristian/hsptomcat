package com.hspedu.tomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/22  15:39
 *
 * @author Mamenchisat
 * @verison 1.0
 * 第一个版本的tomcat，可以完成接受浏览器的请求，并返回信息
 */
public class HspTomcatV1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("====myTomcat在8080端口监听===");
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            //先接收浏览器发生的数据
            //为了读取方便，转换成字符流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line = null;
            System.out.println("===浏览器发送的数据===");
            //循环读取
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() == 0) {
                    break;
                }
                System.out.println(line);
            }
            OutputStream outputStream = socket.getOutputStream();

            //构建一个http响应的头,http响应体需要两个换行
            String respHeader = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n\r\n";
            String response = respHeader + "hi,hspedu 韩顺平教育";
            outputStream.write(response.getBytes());
            outputStream.flush();

            //关闭流和socket
            inputStream.close();
            outputStream.close();
            socket.close();

        }
    }
}
