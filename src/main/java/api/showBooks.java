package api;

import view.HttpGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class showBooks extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = utf-8");
        Writer writer = resp.getWriter();
        HttpSession session = req.getSession(false);

        if (session == null) {
            String html = HttpGenerator.getByMessage("您未登录，点击前往登陆", "login.html");
            writer.write(html);
            return;
        }
        writer.write(HttpGenerator.getAllBooks());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
