<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/2/2
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>文件上传</title>
  </head>
  <body>
<%--  input的type为file，form的enctype="multipart/form-data"--%>
    <form action="/upload" method="post" enctype="multipart/form-data">
      文件名：  <input type="text" name="filename"> <br/>
      选择文件：<input type="file" name="uploadedfile"> <br/>
      <input type="submit" value="上传">
    </form>
  </body>
</html>
