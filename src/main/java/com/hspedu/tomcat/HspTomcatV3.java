package com.hspedu.tomcat;

import com.hspedu.tomcat.handler.HspRequestHandler;
import com.hspedu.tomcat.servlet.HspHttpServlet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/23  16:13
 *
 * @author Mamenchisat
 * @verison 1.0
 * 通过反射+xml来实现初始化容器
 */
public class HspTomcatV3 {
    public static void main(String[] args) {
        HspTomcatV3 hspTomcatV3 = new HspTomcatV3();
        hspTomcatV3.init();
        hspTomcatV3.run();
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("===TomcatV3===");
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                HspRequestHandler hspRequestHandler = new HspRequestHandler(socket);
                new Thread(hspRequestHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final ConcurrentHashMap<String, HspHttpServlet> servletMap = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, String> servletUriMap = new ConcurrentHashMap<>();

    //    直接对两个容器初始化
    public void init() {
        //读取web.xml=>dom4j
        //得到文件路径
        String path = HspTomcatV3.class.getResource("/").getPath();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(path + "web.xml"));
            //System.out.println(document);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                if (element.getName().equals("servlet")) {
                    //这是servlet配置
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");
                    servletMap.put(servletName.getText(),(HspHttpServlet) Class.forName(servletClass.getText().trim()).newInstance());
                }else if (element.getName().equals("servlet-mapping")){
                    //这是servletMapping配置
                    Element servletName = element.element("servlet-name");
                    Element urlPattern = element.element("url-pattern");
                    servletUriMap.put(urlPattern.getText(),servletName.getText());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(servletMap);
//        System.out.println(servletUriMap);
    }
}
