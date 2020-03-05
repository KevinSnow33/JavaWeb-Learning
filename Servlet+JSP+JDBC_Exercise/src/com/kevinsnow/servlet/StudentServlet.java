package com.kevinsnow.servlet;

import com.kevinsnow.entity.Student;
import com.kevinsnow.repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

//   把业务逻辑类的对象设成servlet类中成员变量，使得doGet,doPost中可以公用
    StudentRepository studentRepository = new StudentRepository();
    //对象变量作为类成员变量时可以new！！！！

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type == null){
            type = "retrieve";
        }

        switch (type){
            //查
            case "retrieve":
                //业务逻辑在repository类中的方法中
                List list = studentRepository.retrieve(); //调用持久层的函数

                req.setAttribute("students",list);
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                break;
            //赠
            case "add":
                resp.sendRedirect("add.jsp");
                break;
            //改
                //1.更新需要先到数据库中按id拿到该完整对象并在页面中展示信息
            case "updateFind":
                String idStr = req.getParameter("id");
                int id = Integer.parseInt(idStr);

                Student student = studentRepository.updateFind(id);
                req.setAttribute("student",student);
                req.getRequestDispatcher("update.jsp").forward(req,resp);
                break;
                //2.将用户更改后的信息更新到数据库,此操作在doPost方法中update里
            //删
            case "delete":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                studentRepository.delete(id);
                resp.sendRedirect("/student");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //乱码问题在filter中解决

        String type = req.getParameter("type");

        switch (type){
            //增：  doPost中处理form传过来的数据
            case "add":
                String name = req.getParameter("name");
                String scoreStr = req.getParameter("score");
                double score = Double.parseDouble(scoreStr);
                //html的date类型，传到java中就是yy-MM-dd格式的字符串，因为mySQL中的date类型需要传的是java的字符串，
                // 所以此处保留html传过来的字符串即可
                String birthdayStr = req.getParameter("birthday");
//                System.out.println(birthdayStr);

                //业务逻辑在repository类中的方法中
                studentRepository.add(name,score,birthdayStr);
                resp.sendRedirect("/student");
                break;
            case "update":
                name = req.getParameter("name");
                scoreStr = req.getParameter("score");
                score = Double.parseDouble(scoreStr);
                //html的date类型，传到java中就是yy-MM-dd格式的字符串，因为mySQL中的date类型需要传的是java的字符串，
                // 所以此处保留html传过来的字符串即可
                birthdayStr = req.getParameter("birthday");
                String idStr = req.getParameter("id");
                int id = Integer.parseInt(idStr);

                //业务逻辑在repository类中的方法中
                studentRepository.update(id,name,score,birthdayStr);
                resp.sendRedirect("/student");
                break;
        }

    }
}
