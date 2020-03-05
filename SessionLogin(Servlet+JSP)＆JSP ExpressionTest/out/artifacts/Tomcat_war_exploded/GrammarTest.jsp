<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kevinsnow.servlet.Student" %><%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/1/28
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Servlet测试</title>
  </head>
  <body>

    <%  List Students = new ArrayList();
        Students.add(new Student("张三",20));
        Students.add(new Student("李四",22));
        Students.add(new Student("王二",25));
    %>

    <table border="1">

      <tr>
        <th>姓名：</th>
        <th>年龄：</th>
      </tr>

      <% for (int i = 0; i < Students.size(); i++) {  %>
      <tr>
        <td>
          <%= ((Student)Students.get(i)).getName() %>
        </td>
        <td>
          <%= ((Student)Students.get(i)).getAge() %>
        </td>
      </tr>
      <%}%>



    </table>
  </body>
</html>
