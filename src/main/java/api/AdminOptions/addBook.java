package api.AdminOptions;

import model.Book;
import model.BookDao;
import view.HttpGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class addBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = utf-8");

        HttpSession session = req.getSession(false);
        if (session == null) {
            HttpGenerator.getByMessage("您未登录，点击前往登陆", "loginAdmin.html");
            return;
        }
        String bookName = req.getParameter("bookname");
        String author = req.getParameter("author");
        String remarks = req.getParameter("remarks");
        String body = req.getParameter("body");
        Book book = new Book();
        book.setBookname(bookName);
        book.setAuthor(author);
        book.setRemarks(remarks);
        book.setBody(body);
        if (BookDao.addBook(book)) {
            Writer writer = resp.getWriter();
            writer.write(HttpGenerator.getByMessage("书籍新增成功！ 点击返回管理员菜单", "AdminOptions.html"));
        } else {
            Writer writer = resp.getWriter();
            writer.write(HttpGenerator.getByMessage("操作失败！ 点击返回管理员菜单", "AdminOptions.html"));
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
