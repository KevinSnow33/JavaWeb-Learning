<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/1/30
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<%-- 用请求对象取：
   欢迎回来，<%= request.getAttribute("account") %>！--%>

<%-- 用会话对象取：--%>
    欢迎回来，<%= session.getAttribute("account") %>！
<%--退出登录后，超链接到logout类中执行逻辑，超链接用的是get请求--%>
    <a href="/Tomcat_war_exploded/logout"> 退出登录 </a>

</body>
</html>
