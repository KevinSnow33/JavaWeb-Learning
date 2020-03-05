import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtils_Test {

    //DBUtils在执行数据库查询时，对于返回对象的接受很方便
    public static void findByDBUtils(Integer id){
        Connection connection = null;
        Student student = null;
        try {
            connection = JDBCUtil.getConnection();


            QueryRunner queryRunner = new QueryRunner();
            //queryRunner.query方法的第二个参数：
            //ResultHandlers接⼝是⽤来处理结果集，可以将查询到的结果集转换成 Java 对象，提供了 4 种实现类。

            //1.BeanHandler 将单个结果集映射成 Java 对象 Student
            String sql1 = "select * from students where id = ?";
            student = queryRunner.query(connection,sql1,
                    new BeanHandler<>(Student.class), id);
            //要把类的模板Student.class传进去    同时与sql语句中的"?"响应的参数也要在后面传进去（比如id），个数无限制，由？个数决定）
            System.out.println("BeanHandler:" + student);

            //2.BeanListHandler 将多个结果集映射成 List 集合 List
            String sql2 = "select * from students";
            List studentList = queryRunner.query(connection,sql2,
                    new BeanListHandler<>(Student.class));
            System.out.println("BeanListHandler:" + studentList);

            //3.MapHandler 将单个结果集映射成 Map 对象(key是列名，value是值，比如 id = 1，key是"id"，value是1)
            String sql3 = "select * from students where id = ?";
            Map map = queryRunner.query(connection,sql3,
                    new MapHandler(), id);
            //不需要传类的模板Student.class，因为结果并不封装成类，而是存到map集合中
            System.out.println("MapHandler:" + map);

            //4.MapListHandler 将多个结果集映射成 MapList 结合，即多个map对象组成的list集合，注意<Map<String,Object>>
            String sql4 = "select * from students";
            List<Map<String,Object>> mapList = queryRunner.query(connection,sql4,new MapListHandler());
            System.out.println("MapListHandler:" + mapList);
            //遍历mapList：
//            for (Map<String,Object> map1:mapList){
//                System.out.println(map1);
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DBUtils_Test.findByDBUtils(1);
    }


}
