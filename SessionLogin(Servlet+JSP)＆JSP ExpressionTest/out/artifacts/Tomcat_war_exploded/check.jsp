<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/1/30
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>check</title>
</head>
<body>
<%--   该页是纯java逻辑代码，直接写在servlet页面中更佳，没必要写在jsp中 --%>
    <%
        if(request.getParameter("account").equals("admin") && request.getParameter("password").equals("123")){
            request.setAttribute("account",request.getParameter("account").toString());
            //Dispatcher转发的路径里不用写/Tomcat_war_exploded
            request.getRequestDispatcher("success.jsp").forward(request,response);
        }else {
            //sendRedirec重定向的路径里需要写/Tomcat_war_exploded
            response.sendRedirect("/Tomcat_war_exploded/login.jsp");
        }
    %>
</body>
</html>
