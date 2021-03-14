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

public class registerAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String identity = req.getParameter("identity");
        resp.setContentType("text/html; charset = utf-8");
        Writer writer = resp.getWriter();
        if (!identity.equals("666")) {
            writer.write(HttpGenerator.getByMessage("身份校验码错误，无法注册管理员账号！（测试验证码为666）", "adminLogin.html"));
            return;
        }
        User user = new User();
        user.setUserName(name);
        user.setPassWord(password);
        user.setAdmin(true);
        UserDao.addUser(user);
        writer.write(HttpGenerator.getByMessage("管理员注册成功！请前往登陆", "loginAdmin.html"));


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
