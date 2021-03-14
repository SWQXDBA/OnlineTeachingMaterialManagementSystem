package api.UserOptions;

import model.BookDao;
import model.User;
import view.HttpGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class borrowBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = utf-8");
        HttpSession session = req.getSession(false);
        Writer writer = resp.getWriter();
        if (session == null) {
            writer.write(HttpGenerator.getByMessage("您未登录，点击前往登陆", "login.html"));
            return;
        }
        String bookid = req.getParameter("bookid");
        if (bookid == null) {
            writer.write(HttpGenerator.getByMessage("借书失败,未查询到目标id,点击返回主菜单", "UserOptions.html"));
            return;
        }
        //从session中获取当前访问的用户
        User user = (User) session.getAttribute("user");
        if (BookDao.borrowById(Integer.parseInt(bookid), user.getUserId())) {
            writer.write(HttpGenerator.getByMessage("借书成功！点击返回主菜单", "UserOptions.html"));
            return;
        } else {
            writer.write(HttpGenerator.getByMessage("借书失败,点击返回主菜单", "UserOptions.html"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
