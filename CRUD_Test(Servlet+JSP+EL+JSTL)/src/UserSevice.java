import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//本项目启动链接为 /user，而非index.jsp，因为需要先把map传给index才能显示

@WebServlet("/user")
public class UserSevice extends HttpServlet {

    static Map<Integer,User> map = new HashMap<>();
    static int id = 1;   //id值，随添加对象时自增
    int preId;      //更新时用来保存原对象的id

    static{
        //初始表单里就存在3个人，需要在static语句块或类的构造函数中只执行一次添加代码
        map.put(id,new User(id++,"张三",90));
        map.put(id,new User(id++,"李四",80));
        map.put(id,new User(id++,"王二",100));
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
//      把业务类型用type=add参数传递给doPost
        String type = req.getParameter("type");
        if(type == null){
            type = "default";
        }

        switch(type){
//          默认操作，即不带参数访问http://localhost:8080/user时进行初始的存数据操作
            case "default":
    //       1. 在servlet中获取application对象的方法为this.getServletContext()
    //       2.一定注意 ，map集合的对象传给setAttribute时一定传的是map.values()才是传“值”，否则是将“键值对”一起传过去了！！！
                this.getServletContext().setAttribute("mapKey",map.values());  //存map对象 一定要用.values()！！！
                resp.sendRedirect("index.jsp");
                break;
            case "update":
//                preId是原来对象的id
                preId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("updatedUser",map.get(preId));
                req.getRequestDispatcher("update.jsp").forward(req,resp);
                break;
//          "删"
            case "delete":
//                勿忘参数返回值是字符串，要转换成int
                map.remove(Integer.parseInt(req.getParameter("id")));
                this.getServletContext().setAttribute("mapKey",map.values());  //存新map对象
                resp.sendRedirect("index.jsp");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      解决form提交的中文乱码问题
        req.setCharacterEncoding("UTF-8");
//      把业务类型用type=add参数传递给doPost
        String type = req.getParameter("type");
        if(type == null){
            type = "retrieve";
        }
        switch (type){
//          "查"：
            case "retrieve":
                resp.sendRedirect("index.jsp");
                break;
//          "增"
            case "add":
//              如果用户没输入姓名或成绩，则重新跳转到add页面，避免score和name为null
                try {
                    String name = req.getParameter("inputName");
                    //getParameter方法返回的是String，需要转换成对应类型
                    Double score = Double.parseDouble(req.getParameter("inputScore"));
                    map.put(id, new User(id++, name, score));
                } catch (Exception e) {
                    resp.sendRedirect("add.jsp");
                    break;
                }
//              用户正确输入信息后，setAttribute新map.values()然后跳转到首页
                this.getServletContext().setAttribute("mapKey",map.values());  //存map对象 一定要用.values()！！！
                resp.sendRedirect("index.jsp");
                break;
//          "改"
            case "update":
//              如果用户没输入姓名或成绩，则重新跳转到update页面，避免score和name为null
                try {
                    String name = req.getParameter("inputName");
                    //getParameter方法返回的是String，需要转换成对应类型
                    double score = Double.parseDouble(req.getParameter("inputScore"));

//                  其他地方与add相同，但此处id应该是原来id才能起到更新而非添加的功能
                    int preId = Integer.parseInt(req.getParameter("inputId"));
                    map.put(preId, new User(preId, name, score));

                } catch (Exception e) {
                    //preId是原来对象的id
                    req.setAttribute("updatedUser",map.get(preId));
                    req.getRequestDispatcher("update.jsp").forward(req,resp);
                    break;
                }
//              用户正确输入信息后，setAttribute新map.values()然后跳转到首页
                this.getServletContext().setAttribute("mapKey",map.values());  //存map对象 一定要用.values()！！！
                resp.sendRedirect("index.jsp");
                break;
        }
    }
}
