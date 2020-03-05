<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/2/1
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加学生</title>
</head>
<body>
<%--  把业务类型用type=add参数传递给doPost --%>
    <form action="/user?type=add" method="post">
        姓名：<input type="text" name="inputName"> <br>
        分数：<input type="text" name="inputScore"> <br>
        <input type="submit" value="增加">
    </form>

</body>
</html>
