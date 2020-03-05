<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/2/7
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新学生信息</title>
</head>
<body>
    <h3>更新学生信息</h3>
    <form action="/student?type=update&id=${student.id}" method="post">
        <table>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="name" value="${student.name}"></td>
            </tr>
            </tr>
            <td>分数：</td>
            <td><input type="text" name="score" value="${student.score}"></td>
            </tr>
            <tr>
                <td>出生日期：</td>
    <%--                html的date类型，传到java中就是yy-MM-dd格式的字符串      --%>
                <td><input type="date" name="birthday" value="${student.birthday}"></td>
            </tr>
            <tr>
                <td><input type="submit" value="更新"></td>
            </tr>
        </table>
    </form>

</body>
</html>
