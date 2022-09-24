package com.hspedu.tomcat;

import com.hspedu.tomcat.handler.HspRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/22  16:52
 *
 * @author Mamenchisat
 * @verison 1.0
 */
public class HspTomcatV2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("===HspTomcatV2在8080端口监听===");
        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            HspRequestHandler hspRequestHandler = new HspRequestHandler(socket);
            new Thread(hspRequestHandler).start();
        }
    }
}
