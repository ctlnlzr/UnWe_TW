package edu.tw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MediuController {
    public void create(int luna, String judet, int femeiDinMediulUrban, int barbatiDinMediulUrban,int femeiDinMediulRural, int barbatiDinMediulRural) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql = "insert into mediu values(?, ?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        String lunaJudet = luna + judet;
        preparedStatement.setObject(1, luna);
        preparedStatement.setObject(2, judet);
        preparedStatement.setObject(3, femeiDinMediulUrban);
        preparedStatement.setObject(4, barbatiDinMediulUrban);
        preparedStatement.setObject(5, femeiDinMediulRural);
        preparedStatement.setObject(6, barbatiDinMediulRural);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
   //     connection.close();
    }
}
