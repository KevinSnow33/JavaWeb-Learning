import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class check extends HttpServlet {

    String defaultAccount;
    String defaultPassword;

    @Override
    public void init(ServletConfig config) throws ServletException {
        defaultAccount = config.getInitParameter("account");
        defaultPassword = config.getInitParameter("password");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        if(account.equals(defaultAccount) && password.equals(defaultPassword)){
            Cookie cookie = new Cookie("username", account);
//            设定cookie有效期，以秒为单位，以下为7天的有效期
            cookie.setMaxAge(60*60*24*7);
//            将用户名密码存为一条Cookie返还给Browser
            resp.addCookie(cookie);

            resp.sendRedirect("/success.jsp");
        }else {
            resp.sendRedirect("/login.jsp");
        }
    }
}
