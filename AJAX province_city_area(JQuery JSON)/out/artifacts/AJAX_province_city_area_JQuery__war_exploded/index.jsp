<%--
  Created by IntelliJ IDEA.
  User: Kevin.Snow
  Date: 2020/2/4
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>省市区选择</title>

<%--引入jquery--%>
    <script type="text/javascript" src="js/jquery-3.4.1.js">  </script>

<%--js代码块--%>
    <script type="text/javascript">
      $(function () {
          $("#city").change(function () {
            // this.value是JS原生态写法，$(this).val()是JQ的写法，结果一样，此处与教学视频不符
              var id = this.value;   // 或者var id = $(this).val();,取得所选option的value值
             // alert(id);
            $.ajax({
                  url:'/location',  //跳转到的servlet
                  type:'post',
                  data: 'id='+id,
                  dataType:'JSON',    //返回的数据类型：json对象
                  success:function (data) {    //还有其他回调函数
                      var content
                      for(var i = 0; i < data.length; i++){
                        content += '<option>' + data[i] + '</option>';
                      } //把设置新的各个区名的html语句连在一起赋给content变量

                      $("#area").html(content);  //把新的html语句（元素）赋给select
                      // .html()方法：获取元素内容,如果有子元素，保留标签
                  }

              })
          });
      });

    </script>

  </head>
  <body>
    <h3>省市区选择</h3>

    <input id="try" type="button" value="点此弹出">

    省：
    <select>
      <option>辽宁省</option>
      <option>吉林省</option>
    </select>

    市：
    <select id="city">
      <option value="大连">大连市</option>
      <option value="沈阳">沈阳市</option>
    </select>

    区：
    <select id="area">
      <option>甘井子区</option>
      <option>中山区</option>
      <option>沙河口区</option>
    </select>

  </body>
</html>
