package com.hspedu.tomcat.handler;

import com.hspedu.tomcat.HspTomcatV3;
import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;
import com.hspedu.tomcat.servlet.HspCalServlet;
import com.hspedu.tomcat.servlet.HspHttpServlet;

import java.io.*;
import java.net.Socket;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/22  16:34
 *
 * @author Mamenchisat
 * @verison 1.0
 */
public class HspRequestHandler implements Runnable {
    private Socket socket = null;

    public HspRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //这里可以对客户端或浏览器进行交互
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
//            String line = "";
//            System.out.println("===服务器接收到的数据===");
//            while ((line = bufferedReader.readLine()) != null) {
//                if (line.length() == 0) {
//                    break;
//                }
//                System.out.println(line);
//            }
            HspRequest hspRequest = new HspRequest(inputStream);
//            String num1 = hspRequest.getParameter("num1");
//            String num2 = hspRequest.getParameter("num2");
//            System.out.println("服务器接收到的数据:");
//            System.out.println(num1 + "  " + num2);
//            System.out.println("request" + hspRequest);

            HspResponse hspResponse = new HspResponse(outputStream);
            String uri = hspRequest.getUri();
            String servletName = HspTomcatV3.servletUriMap.get(uri);
            if (servletName == null) {
                servletName = "";
            }
            HspHttpServlet hspHttpServlet = HspTomcatV3.servletMap.get(servletName);
            if (hspHttpServlet == null) {
                String response = HspResponse.responseHeader + "<h1>404 not found!</h1>";
                outputStream.write(response.getBytes());
                outputStream.flush();
                return;
            }
            hspHttpServlet.service(hspRequest, hspResponse);

//            //创建HspCalServlet对象
//            HspCalServlet hspCalServlet = new HspCalServlet();
//            hspCalServlet.doGet(hspRequest,hspResponse);
//            String response = HspResponse.responseHeader + "<h1>hspResponse返回的消息 hi 你好 </h1>";
//            OutputStream outPutStream = hspResponse.getOutPutStream();
//            outPutStream.write(response.getBytes());
//            outPutStream.flush();
//            outPutStream.close();

//            //返回数据
//            OutputStream outputStream = socket.getOutputStream();
//            //构建一个http响应的头,http响应体需要两个换行
//            String respHeader = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: text/html;charset=utf-8\r\n\r\n";
//            String response = respHeader + "<h1>hi,hspedu 韩顺平教育</h1>";
//            System.out.println("===myTomcatV2返回的数据是===");
//            System.out.println(response);
//            outputStream.write(response.getBytes());
//            outputStream.flush();
//            outputStream.close();
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
