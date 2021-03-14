package api.AdminOptions;

import model.BookDao;
import view.HttpGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class deleteBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = utf-8");
        HttpSession session = req.getSession(false);
        if (session == null) {
            HttpGenerator.getByMessage("您未登录，点击前往登陆", "loginAdmin.html");
            return;
        }
        int bookid = Integer.parseInt(req.getParameter("bookid"));
        if (BookDao.delete(bookid)) {
            Writer writer = resp.getWriter();
            writer.write(HttpGenerator.getByMessage("书籍删除完成！ 点击返回管理员菜单", "AdminOptions.html"));
        } else {
            Writer writer = resp.getWriter();
            writer.write(HttpGenerator.getByMessage("操作失败！请检查输入的书籍id是否合法 点击返回管理员菜单", "AdminOptions.html"));
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
