package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static void addUser(User user) {
        Connection connection = dbUtil.getConnection();
        String sql = "insert into user values(null,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassWord());
            statement.setBoolean(3, user.isAdmin());
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("插入新用户失败");
                return;
            }
            System.out.println("插入新用户成功！");
            System.out.println(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.close(connection, statement, null);
        }
    }

    public static User selectUserByName(String name) {
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "select * from user where userName = ?";
        User user = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            //当 ResultSet 为非空时，其游标指向第一条记录前面，若为空时由于不存在第一条记录，所以这时候游标也无法向指第一条记录前面
            if (!resultSet.isBeforeFirst()) {
                System.out.println("查找不到用户");
                return null;
            }
            if (resultSet.next()) {
                user = new User(resultSet.getString("userName"),
                        resultSet.getString("passWord"),
                        resultSet.getInt("userId"),
                        resultSet.getBoolean("isAdmin"));
            }
            System.out.println(user);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.close(connection, statement, resultSet);
        }
        return user;
    }

    public static User selectUserById(int id) {
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "select * from user where userId = ?";
        User user = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            //当 ResultSet 为非空时，其游标指向第一条记录前面，若为空时由于不存在第一条记录，所以这时候游标也无法向指第一条记录前面
            if (!resultSet.isBeforeFirst()) {
                // System.out.println("查找不到用户");
                return null;
            }
            if (resultSet.next()) {
                user = new User(resultSet.getString("userName"),
                        resultSet.getString("passWord"),
                        resultSet.getInt("userId"),
                        resultSet.getBoolean("isAdmin"));
            }
            System.out.println(user);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.close(connection, statement, resultSet);
        }
        return user;
    }

    public static User selectUser(String name, String password) {
        Connection connection = dbUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "select * from user where userName = ? and passWord = ?";
        User user = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            //当 ResultSet 为非空时，其游标指向第一条记录前面，若为空时由于不存在第一条记录，所以这时候游标也无法向指第一条记录前面
            if (!resultSet.isBeforeFirst()) {
                System.out.println("查找不到用户");
                return null;
            }
            if (resultSet.next()) {
                user = new User(resultSet.getString("userName"),
                        resultSet.getString("passWord"),
                        resultSet.getInt("userId"),
                        resultSet.getBoolean("isAdmin"));
            }
            System.out.println(user);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.close(connection, statement, resultSet);
        }
        return user;
    }

//    public static void main(String[] args) {
////        User user = new User();
////        user.setAdmin(false);
////        user.setUserName("xiaoming2");
////        user.setPassWord("aaa2");
////
////        addUser(user);
//        selectUserByName("xiaominG2");
//    }
}
