package com.kevinsnow.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

//注解方式映射servlet类
@WebServlet("/test")
public class servlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("服务器端已响应");
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.getWriter().write("我是服务器应用程序汤姆猫！");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
