package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    //    public static void main(String[] args) {
//        Book book = new Book();
//        book.setBookname("bookName");
//        book.setAuthor("author");
//        book.setRemarks("remarks");
//        book.setBody("body");
//        addBook(book);
//    }
    public static boolean addBook(Book book) {
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;

        String sql = "insert into books (bookName,author,remarks,body) values(?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, book.getBookname());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getRemarks());
            statement.setString(4, book.getBody());
            int ret = statement.executeUpdate();
            if (ret == -1) {
                System.out.println("书籍新增失败！");
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.close(connection, statement, null);
        }
        return true;


    }

    public static List<Book> selectAllBooks() {
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> bookList = new ArrayList<Book>();
        String sql = "select * from books";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet == null) {
                System.out.println("查找失败！");
                return null;
            }
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("bookId"));
                book.setBookname(resultSet.getString("bookName"));
                book.setAuthor(resultSet.getString("author"));
                book.setBorrowed(resultSet.getBoolean("isBorrowed"));
                book.setUserId(resultSet.getInt("userid"));
                book.setRemarks(resultSet.getString("remarks"));
                bookList.add(book);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.close(connection, statement, resultSet);
        }
        return bookList;
    }

    public static List<Book> selectUserBooks(int userid) {
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> bookList = new ArrayList<Book>();
        String sql = "select * from books where userid = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userid);
            resultSet = statement.executeQuery();
            if (resultSet == null) {
                System.out.println("查找失败！");
                return null;
            }
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("bookId"));
                book.setBookname(resultSet.getString("bookName"));
                book.setAuthor(resultSet.getString("author"));
                book.setBorrowed(resultSet.getBoolean("isBorrowed"));
                book.setUserId(resultSet.getInt("userid"));
                book.setRemarks(resultSet.getString("remarks"));
                bookList.add(book);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.close(connection, statement, resultSet);
        }
        return bookList;
    }


    public static Book selectById(int id) {
        Book book = new Book();
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "select * from books where bookid = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                System.out.println("查询书籍id失败");
                return null;
            }
            if (resultSet.next()) {
                book.setBookId(resultSet.getInt("bookId"));
                book.setBookname(resultSet.getString("bookName"));
                book.setAuthor(resultSet.getString("author"));
                book.setBorrowed(resultSet.getBoolean("isBorrowed"));
                book.setUserId(resultSet.getInt("userid"));
                book.setRemarks(resultSet.getString("remarks"));
                book.setBody(resultSet.getString("body"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }


    public static boolean borrowById(int id, int userId) {
        Book book = new Book();
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "update books set isBorrowed= 1,userid = ? where bookid = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, id);
            int ret = statement.executeUpdate();
            if (ret == -1) {
                //   System.out.println("查询书籍id失败");
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean returnById(int id, int userid) {
        Book book = new Book();
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;
        book = selectById(id);
        //确认操作的用户id和借书的id匹配
        if (book != null && book.getUserId() == userid) {
            //记得将userid也置为null
            String sql = "update books set isBorrowed = 0, userid = null where bookid = ?";
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                int ret = statement.executeUpdate();
                if (ret == -1) {
                    //   System.out.println("查询书籍id失败");
                    return false;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean delete(int id) {
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "delete from books where bookId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int ret = statement.executeUpdate();
            if (ret == -1 || ret == 0) {
                System.out.println("删除失败");
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.close(connection, statement, null);
        }
        return true;
    }
}