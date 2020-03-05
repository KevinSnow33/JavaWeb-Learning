import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("username")){
                cookie.setMaxAge(0);
//                更改完cookie后，千万记得把cookie在response中送回去
                resp.addCookie(cookie);

            }
        }

        int a = 10/0;

        resp.sendRedirect("/login.jsp");
    }
}
