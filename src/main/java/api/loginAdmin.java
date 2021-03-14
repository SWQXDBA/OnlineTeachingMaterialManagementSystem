package api;

import model.User;
import model.UserDao;
import view.HttpGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class loginAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = UserDao.selectUser(name, password);
        resp.setContentType("text/html; charset = utf-8");
        Writer writer = resp.getWriter();
        if (user == null) {
            writer.write(HttpGenerator.getByMessage("账号或密码错误，请再尝试", "login.html"));
            return;
        } else {

            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            writer.write(HttpGenerator.getByMessage("登陆成功", "AdminOptions.html"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
