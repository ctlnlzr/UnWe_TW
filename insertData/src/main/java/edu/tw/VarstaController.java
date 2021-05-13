package edu.tw;

import java.sql.Connection;
import java.sql.*;

public class VarstaController {
    public void create(int luna, String judet, int sub25, int sub29, int sub39, int sub49, int sub55, int peste55) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql = "insert into varsta values(?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, luna);
        preparedStatement.setObject(2, judet);
        preparedStatement.setObject(3, sub25);
        preparedStatement.setObject(4, sub29);
        preparedStatement.setObject(5, sub39);
        preparedStatement.setObject(6, sub49);
        preparedStatement.setObject(7, sub55);
        preparedStatement.setObject(8, peste55);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
       // connection.close();
    }
}
