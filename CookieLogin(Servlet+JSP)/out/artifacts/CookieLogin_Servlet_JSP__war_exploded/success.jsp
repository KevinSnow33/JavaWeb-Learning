<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/1/30
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
    <h1>登录成功</h1>
    <p>欢迎回来,
    <%
        Cookie[] cookies = request.getCookies();
        //浏览器本身Cookie里就有一些别的信息，我们需要遍历cookies找到用户名密码这条Cookie
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("username")){
                out.write(cookie.getValue());
            }
        }
    %>
    </p>
    <a href="/logout">退出登录</a>
</body>
</html>
