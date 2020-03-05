<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/2/1
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--本项目启动链接为 /user，而非index.jsp，因为需要先把map传给index才能显示--%>
<html>
  <head>
    <title>学生名单</title>
  </head>
  <body>
    <table>

<%--      EL + JSTL           --%>
      <c:forEach items="${mapKey}" var="eachUser">
        <tr>
          <td>${eachUser.id}</td>
          <td>${eachUser.name}</td>
          <td>${eachUser.score}</td>
          <td><a href="/user?type=update&id=${eachUser.id}">更新</a> </td>
          <td><a href="/user?type=delete&id=${eachUser.id}">删除</a> </td>
        </tr>
      </c:forEach>
    </table>

    <a href="add.jsp">增加学生</a>

    <hr/>
    <a href="/user">点此刷新名单（或访问http://localhost:8080/user）</a>
  </body>
</html>
