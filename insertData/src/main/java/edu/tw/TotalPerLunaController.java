package edu.tw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TotalPerLunaController {
    public void create(int luna, String judet, int total, float rata) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql = "insert into totalPerLuna  values(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        String lunaJudet = luna + judet;
        preparedStatement.setObject(1, luna);
        preparedStatement.setObject(2, judet);
        preparedStatement.setObject(3, total);
        preparedStatement.setObject(4, rata);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
     //   connection.close();
    }
}
