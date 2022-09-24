package com.hspedu.tomcat.http;

import java.io.OutputStream;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/22  19:20
 *
 * @author Mamenchisat
 * @verison 1.0
 * HspResponse 对象可以封装OutPutStream(与socket)关联
 * 即可以通过HspResponse对象，返回http响应给浏览器/客户端
 * 等价于原生的httpServletResponse
 */
public class HspResponse {
    private OutputStream outPutStream = null;
    //写一个响应头
    public static final String responseHeader = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html;charset=utf-8\r\n\r\n";

    public HspResponse(OutputStream outPutStream) {
        this.outPutStream = outPutStream;
    }



    public OutputStream getOutPutStream() {
        return outPutStream;
    }

    public void setOutPutStream(OutputStream outPutStream) {
        this.outPutStream = outPutStream;
    }
}
