package view;

import model.Book;
import model.BookDao;
import model.UserDao;

import java.util.List;

public class HttpGenerator {
    public static String getByMessage(String message, String url) {
        StringBuilder str = new StringBuilder();
        str.append("<html>");
        str.append("<head>");
        str.append("<meta charset=\"UTF-8\">");
        str.append("<title>提示页面,.</title>");
        str.append("</head>");
        str.append("<body>");
        str.append("<h3>");
        str.append(message);
        str.append("</h3>");
        str.append(String.format("<a href=\"%s\">点击跳转</a>", url));
        str.append("</body>");
        str.append("</html>");

        return str.toString();
    }

    public static String getAllBooks() {
        StringBuilder str = new StringBuilder();
        str.append("<html>");
        str.append("<head>");
        str.append("<meta charset=\"UTF-8\">");
        str.append("<title>提示页面</title>");
        str.append("</head>");
        str.append("<body>");

        List<Book> books = BookDao.selectAllBooks();
        if (books.isEmpty()) {
            str.append("没找到任何书籍!");
        } else {
            for (Book book : books) {
                str.append("<h3>");
                str.append("书籍编号: " + book.getBookId() + "  书名: " + book.getBookname() + "  作者： " + book.getAuthor() + "  备注： " + book.getRemarks() + "  ");
                if (book.isBorrowed()) {
                    String name = UserDao.selectUserById(book.getUserId()).getUserName();
                    if (name != null) {
                        str.append("借书者： " + name);
                    } else {
                        str.append("书籍丢失！！借书者id： " + book.getUserId());
                    }
                } else {
                    str.append("书籍未借出  ");
                    str.append("<a href=\"borrowBook?bookid=" + book.getBookId() + "\">点击借阅</a>");
                }
                str.append("</h3>");
                str.append(String.format("<a href=\"bookDetail?bookid=%d\">点击查看书籍正文</a>", book.getBookId()));
                str.append("<br>");
            }
        }
        str.append("<input type=\"button\" name=\"Submit\" onclick=\"javascript:history.back(-1);\" value=\"返回上一页\">");
        str.append("</body>");
        str.append("</html>");
        return str.toString();
    }

    public static String getBorrowedBooks(int userid) {
        StringBuilder str = new StringBuilder();
        str.append("<html>");
        str.append("<head>");
        str.append("<meta charset=\"UTF-8\">");
        str.append("<title>提示页面</title>");
        str.append("</head>");
        str.append("<body>");

        List<Book> books = BookDao.selectUserBooks(userid);
        if (books.isEmpty()) {
            str.append("没找到任何书籍!");
        } else {
            for (Book book : books) {
                str.append("<h3>");
                str.append("书籍编号: " + book.getBookId() + "  书名: " + book.getBookname() + "  作者： " + book.getAuthor() + "  备注： " + book.getRemarks() + "  ");
                //还书按钮
                str.append("<a href=\"returnBook?bookid=" + book.getBookId() + "\">点击归还</a>");

                str.append("</h3>");
                str.append(String.format("<a href=\"bookDetail?bookid=%d\">点击查看书籍正文</a>", book.getBookId()));
                str.append("<br>");
            }
        }
        str.append("<input type=\"button\" name=\"Submit\" onclick=\"javascript:history.back(-1);\" value=\"返回上一页\">");
        str.append("</body>");
        str.append("</html>");
        return str.toString();
    }


    public static String getBookBodyHtml(Book book) {
        StringBuilder str = new StringBuilder();
        str.append("<html>");
        str.append("<head>");
        str.append("    <meta charset=\"UTF-8\">\n" +
                "    <title>阅读</title>");
        str.append("</head>");
        str.append("<body>");
        str.append(book.getBody().replace("\n", "<br>"));
        str.append("<br>");
        str.append("<input type=\"button\" name=\"Submit\" onclick=\"javascript:history.back(-1);\" value=\"返回上一页\">");
        // str.append(String.format("<a href=\"%s\">返回图书列表</a>","showBooks"));
        str.append("</body>");
        str.append("</html>");
        return str.toString();
    }

}
