package with_XML;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
public class C3P0_Test {
    public static void main(String[] args) {
        try {

        //C3P0数据库连接池的创建(通过关联xml文件的方式)：
            ComboPooledDataSource dataSource = new ComboPooledDataSource("testc3p0");
            //1.xml文件名一定要是c3p0-config.xml
            //2.此处形参（"testc3p0")）里configName一定要和xml文件里的相同
            

        //连接池的使用：
            //拿到连接池中的连接：
            Connection connection = dataSource.getConnection();
            //验证通过连接池方式拿到的connection对象
            System.out.println(connection);
            //将连接还回到数据库连接池中（此close与无连接池JDBC的.close方法不同，是“归还”不是“关闭”连接）
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
