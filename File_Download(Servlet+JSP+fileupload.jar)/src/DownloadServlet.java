import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置响应⽅式(调用浏览器下载工具)
        resp.setContentType("application/x-msdownload");
        //设置下载之后的⽂件名
        String name = "test.jpeg";
        resp.setHeader("Content-Disposition","attachment;filename=" + name);
        //获取输出流
        OutputStream outputStream = resp.getOutputStream();
        //获取文件路径以及输入流
        String path = req.getServletContext().getRealPath("file/test.jpeg");
        InputStream inputStream = new FileInputStream(path);

        int temp;
        while ((temp = inputStream.read()) != -1){
            outputStream.write(temp);
        }

        inputStream.close();
        outputStream.close();
        System.out.println("下载成功");
    }
}
