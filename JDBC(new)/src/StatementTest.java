import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatementTest {
    public static void main(String[] args) {
        try {
        //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取链接
//          //数据库的url+具体数据库名+避免中文乱码
            String url = "jdbc:mysql://localhost:3306/how2java?" +
                    "useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String password = "admin";
            //建立链接
            Connection connection = DriverManager.getConnection(url,user,password);

        //3.【查询】语句操作(用executeQuery语句并返回ResultSet结果集合)
            String sqlRetrieve = "select * from shoter";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRetrieve);

            //遍历resultSet集合
            while (resultSet.next()){
              //两种取元素方式，1是通过列名（String），2是通过列的索引（int）
                int id = resultSet.getInt("id");
//                resultSet.getInt(1);
                String name = resultSet.getString("name");
//                resultSet.getString(2);
                Float hp = resultSet.getFloat(3);
                int damage = resultSet.getInt(4);
//                System.out.println("第"+"位选手信息为："+name+"-"+hp+"-"+damage);
            }

        //4.非查询语句操作（用execute语句或executeUpdate语句，所有对数据库有更改的语句都叫update）
            //增加记录
            String sqlInsert = "insert into shoter(name, hp, damage) values ('新的枪王',100,150)";
            Statement statement1 = connection.createStatement();
            statement1.execute(sqlInsert);

            //更改记录
            String sqlUpdate = "update shoter set name = '新的垃圾' where name = '新的枪王'";
            Statement statement2 = connection.createStatement();
            statement2.execute(sqlUpdate);

            //删除记录
            String sqlDelete = "delete from shoter where name = '新的垃圾'";
            Statement statement3 = connection.createStatement();
            statement3.execute(sqlDelete);




        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
