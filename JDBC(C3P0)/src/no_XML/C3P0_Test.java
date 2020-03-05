package no_XML;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0_Test {
    public static void main(String[] args) {
        try {

        //C3P0数据库连接池的创建：
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/students_servlet_jsp_jdbc?useUnicode=true&characterEncoding=UTF-8");
            dataSource.setUser("root");
            dataSource.setPassword("admin");

        //常用设置方法：
            //设置初始化连接池中的连接个数
            dataSource.setInitialPoolSize(20);
            //设置最大连接数（因为连接数在初始化后可以后续增加）
            dataSource.setMaxPoolSize(40);
            //每次连接个数不够时，再多申请的连接个数（比如20个连接不够时，就再从数据库申请5个连接）
            dataSource.setAcquireIncrement(5);
            //设置“连接个数不够”的门限值(比如20个连接只剩下2个时，触发“连接个数不够”，去多申请几个连接)
            dataSource.setMinPoolSize(2);

        //连接池的使用：
            //拿到连接池中的连接：
            Connection connection = dataSource.getConnection();
            //验证通过连接池方式拿到的connection对象
            System.out.println(connection);
            //将连接还回到数据库连接池中（此close与无连接池JDBC的.close方法不同，是“归还”不是“关闭”连接）
            connection.close();

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
