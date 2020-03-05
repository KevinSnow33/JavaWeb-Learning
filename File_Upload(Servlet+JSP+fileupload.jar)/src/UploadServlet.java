import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

//需要导入lib下的两个jar才能用fileupload组件

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            //利用uploadfile组件获取req中FileItem对象（即抽象的上传的文件）的集合
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(req);
            //遍历存有FileItem对象的集合
            for(FileItem fileItem: list){

                //拿到文件本身的名字
                System.out.println(fileItem.getName());
                //拿到<input>中对应的name
                System.out.println(fileItem.getFieldName());

                //先判断fileItem是不是文本框text输入的内容，如果不是，则是真正的文件
                if(fileItem.isFormField()){
                    //如果是文本框，则后台打印相关信息
                    String name = fileItem.getFieldName();  //取得文本框text的key
                    String value = fileItem.getString("UTF-8"); //取得文本框text的value，即用户输入的内容,形参可设置编码格式避免乱码
                    System.out.println(name + ":" + value);
                }else{
                //1.如果是文件，先后台打印相关信息:

                    String filename = fileItem.getName(); //取得文件本身的名字
                    long size = fileItem.getSize();  //文件大小
                    System.out.println("文件名为：" + filename + ",大小为：" + size + "k");

                //2.真正的将所上传文件用字节流存入服务器中的操作：

                    //用 字节流 是因为字节流按文件原本的字节逐个复制，不会漏掉换行符也不会毁坏图片
                    InputStream inputStream = fileItem.getInputStream();  //获取输入字节流
                    //用ServletContext对象的方法获取项目部署后，某个内容的真实路径（而非源代码路径），比如形参里写（“index.jsp”）就是该文件的绝对路径
                    String path = req.getServletContext().getRealPath("file/" + filename);
                    OutputStream outputStream = new FileOutputStream(path); //按path获取输出字节流

                    int len;
                    while ((len = inputStream.read()) != -1){
                        outputStream.write(len);
                    }

                    inputStream.close();
                    outputStream.close();
                    System.out.println("上传成功！");
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
