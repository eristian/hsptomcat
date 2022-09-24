package com.hspedu.tomcat.servlet;

import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;

import java.io.IOException;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/22  19:16
 *
 * @author Mamenchisat
 * @verison 1.0
 */
public interface HspServlet {
    void init() throws Exception;


    void service(HspRequest request , HspResponse response) throws IOException;


    void destroy();
}
