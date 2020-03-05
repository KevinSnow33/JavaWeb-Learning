package com.kevinsnow.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//用到初始化参数映射，所以该类也在web.xml中进行映射
public class check extends HttpServlet {

    String initAccount;
    String initPassword;

//    用config对象的getInitParameter方法取出InitParameter并保存在变量中供doPost方法里判断使用
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initAccount = config.getInitParameter("account");
        initPassword = config.getInitParameter("password");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

        if(req.getParameter("account").equals(initAccount) && req.getParameter("password").equals(initPassword)){
//       会话：    根据请求对象req，自行创建一个对应的会话对象session（servlet里和jsp不一样session不是内置对象需要自己建）
            HttpSession session = req.getSession();
//       会话：    用session(而非req)的setAttribute方法把参数存起来
            session.setAttribute("account", req.getParameter("account").toString());
//       请求：    最后还是用req请求的方法跳转
            //Dispatcher转发的路径里不用写/Tomcat_war_exploded
            req.getRequestDispatcher("/success.jsp").forward(req,resp);
        }else{
            //sendRedirec重定向的路径里需要写/Tomcat_war_exploded
            resp.sendRedirect("/Tomcat_war_exploded/login.jsp");
        }

    }
}
