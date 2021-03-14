package model;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//管理数据库连接
public class dbUtil {
    private static final String url = "jdbc:mysql://127.0.0.1:3307/TeachingManagement?characterEncoding=utf-8&useSSL=true";
    private static final String userName = "root";
    private static final String password = "q12345";
    private static DataSource dataSource = null;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (dbUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource) dataSource).setURL(url);
                    ((MysqlDataSource) dataSource).setUser(userName);
                    ((MysqlDataSource) dataSource).setPassword(password);
                }

            }
        }
        return dataSource;
    }

    //获取连接
    public static Connection getConnection() {
        try {
            Connection connection = getDataSource().getConnection();
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
