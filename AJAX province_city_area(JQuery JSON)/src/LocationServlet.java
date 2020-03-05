import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/location")
public class LocationServlet extends HttpServlet {

    //建立存取各城市各个区的map：key=城市名，value=存有该城市各区名的list
    static Map<String, List> areaMap = new HashMap<>();
    static {
        //大连的区的list
        List<String> list = new ArrayList<>();
        list.add("甘井子区");
        list.add("沙河口区");
        list.add("中山区");
        areaMap.put("大连",list);
        //沈阳的区的list
        list = new ArrayList<>();
        list.add("皇姑区");
        list.add("铁西区");
        list.add("沈河区");
        areaMap.put("沈阳",list);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cityID = req.getParameter("id");  //获取传过来的城市名
        List areaList = areaMap.get(cityID); //获取该城市各个区名的list
    // 将java对象转化为json对象方法
        // JSONObject jsonObject = JSONObject.fromObject(user);  //转换单个对象：
        JSONArray jsonArray = JSONArray.fromObject(areaList);   //转换集合对象：
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonArray.toString());  //不能用转发或重定向，而应该只把数据写出去，即对应ajax中的success（）的data
    }
}
