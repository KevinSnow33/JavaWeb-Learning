<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/1/30
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<%--跳转到JSP方式：
        action的路径一定要写对，应该是url中ip和端口后的所有内容，且最后勿忘“.jsp”
        action既可以跳转到jsp，也可以跳转到servlet中
 --%>
<%--    <form action="/Tomcat_war_exploded/check.jsp" method="post">--%>

<%--跳转到servlet方式：       --%>

    <form action="/Tomcat_war_exploded/check" method="post">
        账户：<input type="text" name="account"> <br/>
        密码：<input type="password" name="password"> <br/>
        <input type="submit" value="登录" >
    </form>

</body>
</html>
