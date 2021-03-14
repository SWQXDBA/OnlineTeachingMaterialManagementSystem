package api.UserOptions;

import model.User;
import view.HttpGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class selectBorrowedBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = utf-8");
        Writer writer = resp.getWriter();
        HttpSession session = req.getSession(false);
        if (session == null) {
            writer.write(HttpGenerator.getByMessage("您未登录，点击前往登陆", "login.html"));
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            writer.write(HttpGenerator.getByMessage("用户查询失败,请前往登陆", "login.html"));
            return;
        }
        int userid = user.getUserId();
        String str = HttpGenerator.getBorrowedBooks(userid);
        if (str == null) {
            writer.write(HttpGenerator.getByMessage("str错误！", "login.html"));
            return;
        }
        writer.write(str);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
