<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/2/1
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新学生数据</title>
</head>
<body>
    <p>您要更改的学生信息如下：</p>
    <form action="/user?type=update" method="post">
<%--        用value可以将原来的信息显示在文本框内  readonly使id成为只读属性  --%>
        ID：<input type="text" name="inputId" value="${updatedUser.id}" readonly > <br>
        姓名：<input type="text" name="inputName" value="${updatedUser.name}"> <br>
        分数：<input type="text" name="inputScore" value="${updatedUser.score}"> <br>
        <input type="submit" value="更新">
    </form>

</body>
</html>
