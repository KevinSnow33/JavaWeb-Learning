<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/1/30
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录系统</title>
</head>
<body>

    <form action="/check" method="post">
        <table>
            <tr>
                <td>账号：</td>
                <td><input type="text" name="account"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>

</body>
</html>
