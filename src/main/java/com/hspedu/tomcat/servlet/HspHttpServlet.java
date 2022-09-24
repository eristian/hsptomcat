package com.hspedu.tomcat.servlet;

import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;

import java.io.IOException;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/22  19:17
 *
 * @author Mamenchisat
 * @verison 1.0
 */
public abstract class HspHttpServlet implements HspServlet {

    @Override
    public void init() throws Exception {

    }

    @Override
    public void service(HspRequest request, HspResponse response) throws IOException {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            this.doGet(request, response);
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            this.doPost(request, response);
        }
    }

    //这里使用了抽象模板设计模式
    //让HspHttpServlet 的子类来实现 HspCalServlet 实现
    public abstract void doGet(HspRequest request, HspResponse response);

    public abstract void doPost(HspRequest request, HspResponse response);

    @Override
    public void destroy() {

    }
}
