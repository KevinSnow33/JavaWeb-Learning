package com.kevinsnow.repository;

import com.kevinsnow.entity.Student;
import com.kevinsnow.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    List<Student> list = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Student student = null;


    public List<Student> retrieve(){

        list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sqlRetrieve = "select * from students_servlet_jsp_jdbc.students";
            preparedStatement = connection.prepareStatement(sqlRetrieve);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Double score = resultSet.getDouble(3);
                Date birthday = resultSet.getDate(4);
                student = new Student(id,name,score,birthday);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(connection,preparedStatement,resultSet);
        }
        return list;
    }




    public void add(String name, double score, String birthday){
        try {
            connection = JDBCUtil.getConnection();
            String sqlCreate = "insert into students_servlet_jsp_jdbc.students (name, score, birthday) " +
                    "VALUES(?,?,?) ";
            preparedStatement = connection.prepareStatement(sqlCreate);
            preparedStatement.setString(1,name);
            preparedStatement.setDouble(2,score);
            //mySQL中的date类型实际需要传的是java中"yy-MM-dd"格式的字符串
            preparedStatement.setString(3,birthday);
            //executeUpdate的返回值是int型，代表语句执行结果
            int result = preparedStatement.executeUpdate();
            System.out.println("add结果为：" + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(connection,preparedStatement,resultSet);
        }
    }



    //更新时先根据id找到对象
    public Student updateFind(int id){
        try {
            connection = JDBCUtil.getConnection();
            String sqlfind = "select * from students_servlet_jsp_jdbc.students where id = ?";
            preparedStatement = connection.prepareStatement(sqlfind);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String name  = resultSet.getString("name");
                double score = resultSet.getDouble("score");
                java.util.Date birthday = resultSet.getDate("birthday");
                student = new Student(id,name,score,birthday);
                System.out.println("用户所要更新的对象信息为：" + student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(connection,preparedStatement,resultSet);
        }
        return student;
    }
    //然后再把用户修改后的数据更新到数据库
    public void update(int id,String name,double score,String birthday){
        try {
            connection = JDBCUtil.getConnection();
            String sqlUpdate = "update students_servlet_jsp_jdbc.students set name = ?,score = ?,birthday = ? where id = ?";
            preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1,name);
            preparedStatement.setDouble(2,score);
            preparedStatement.setString(3,birthday);
            preparedStatement.setInt(4,id);
            int result = preparedStatement.executeUpdate();
            System.out.println("update结果为：" + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(connection,preparedStatement,null);
        }
    }




    public void delete(int id){
        try {
            connection = JDBCUtil.getConnection();
            String sqlDelete = "delete from students_servlet_jsp_jdbc.students where id = ?";
            preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1,id);
            int result = preparedStatement.executeUpdate();
            System.out.println("delete结果为：" + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(connection,preparedStatement,null);
        }
    }



    //用main方法先测试一下能否拿到数据库中对象
//    public static void main(String[] args) {
//        StudentRepository studentRepository = new StudentRepository();
//        for(Student student:studentRepository.retrieve()){
//            System.out.println(student.toString());
//        }
//    }
}
