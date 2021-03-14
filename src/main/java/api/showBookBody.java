package api;

import model.BookDao;
import view.HttpGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class showBookBody extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = utf-8");
        HttpSession session = req.getSession(false);

        Writer writer = resp.getWriter();
        if (session == null) {
            String html = HttpGenerator.getByMessage("您未登录，点击前往登陆", "login.html");
            writer.write(html);
            return;
        }
        int id = Integer.parseInt(req.getParameter("bookid"));
        writer.write(HttpGenerator.getBookBodyHtml(BookDao.selectById(id)));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
