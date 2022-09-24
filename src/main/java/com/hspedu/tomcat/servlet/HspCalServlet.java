package com.hspedu.tomcat.servlet;

import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;
import com.hspedu.utils.WebUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 项目名：    hsptomcat
 * 创建时间：   2022/9/22  19:17
 *
 * @author Mamenchisat
 * @verison 1.0
 */
public class HspCalServlet extends HspHttpServlet {
    @Override
    public void doGet(HspRequest request, HspResponse response) {
        //写业务代码，完成计算对象
        String strNum1 = request.getParameter("num1");
        String strNum2 = request.getParameter("num2");
        int parseInt1 = WebUtils.parseInt(strNum1, 0);
        int parseInt2 = WebUtils.parseInt(strNum2, 0);
        int result = parseInt1 + parseInt2;
        OutputStream outPutStream = response.getOutPutStream();
        String respMes = HspResponse.responseHeader + "<h1>" + strNum1 + " + " + strNum2 + " = " + result + " myTomcatV3 - 反射" + "</h1>";
        try {
            outPutStream.write(respMes.getBytes());
            outPutStream.flush();
            outPutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HspRequest request, HspResponse response) {
        doGet(request, response);
    }
}
