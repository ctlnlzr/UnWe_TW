package edu.tw;

import java.sql.Connection;
import java.sql.*;

public class Database {
    private static Connection connection = null;

    static {
        String url = "mysql://eu-cdbr-west-01.cleardb.com/UnWe";
        String user = "bb065418178d4c";
        String password = "9014f189";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://eu-cdbr-west-01.cleardb.com:3306/heroku_ea53e347089df57?user=bb065418178d4c&password=9014f189");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        return connection;
    }
}
