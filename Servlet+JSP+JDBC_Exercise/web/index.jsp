<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/2/6
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>学生名单</title>
  </head>
  <body>
    <h3>学生管理系统</h3>
    <table>
      <c:forEach items="${students}" var="student">
        <tr>
          <td>编号：${student.id}    </td>
          <td>姓名：${student.name}    </td>
          <td>分数：${student.score}    </td>
          <td>出生日期：${student.birthday}</td>
          <td><a href="/student?type=updateFind&id=${student.id}">更新</a> </td>
          <td><a href="/student?type=delete&id=${student.id}">删除</a> </td>
        </tr>
      </c:forEach>
    </table>
    <br/>
    <a href="/student?type=add">点此添加学生</a>



  </body>
</html>
