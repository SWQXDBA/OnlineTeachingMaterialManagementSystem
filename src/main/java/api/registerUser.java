package api;

import model.User;
import model.UserDao;
import view.HttpGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class registerUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = utf-8");
        String username = req.getParameter("name");
        String password = req.getParameter("password");
        Writer writer = resp.getWriter();
        String html = null;
        if (UserDao.selectUserByName(username) != null) {
            html = HttpGenerator.getByMessage("用户名重复！请重新注册", "register.html");
        } else {
            User user = new User();
            user.setUserName(username);
            user.setPassWord(password);
            user.setAdmin(false);
            UserDao.addUser(user);
            html = HttpGenerator.getByMessage("注册成功！请登录", "login.html");
        }
        writer.write(html);
    }
}
