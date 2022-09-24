package com.hspedu.servlet;

import com.hspedu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 项目名：    hsptomcat
 * 文件名：    ${NAME}
 * 创建时间：   2022/9/22  11:15
 * @author Mamenchisat
 * 描述：      TODO
 */
@WebServlet(name = "CalServlet", value = "/calServlet")
public class CalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收提交的数据进行计算
        String strNum1 = request.getParameter("num1");
        String strNum2 = request.getParameter("num2");
        int parseInt1 = WebUtils.parseInt(strNum1, 0);
        int parseInt2 = WebUtils.parseInt(strNum2, 0);
        int result = parseInt1 + parseInt2;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>" + strNum1 + " + " + strNum2 + " = " + result + "</h1>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
