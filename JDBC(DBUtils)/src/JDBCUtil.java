import java.sql.*;

public class JDBCUtil {
    //因为每次获取connection时用到参数都一样，所以变量设为静态
    static String username = "root";
    static String password = "admin";
    static String url = "jdbc:mysql://localhost:3306/students_servlet_jsp_jdbc?" +
            "useUnicode=true&characterEncoding=UTF-8";
    static Connection connection;

    //因为驱动只需要加载一次，所以写在静态块中
    static  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //获取链接的方法
    static public Connection getConnection(){
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    //释放链接的方法
    static public void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            if (connection != null)
                connection.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if(resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
