package edu.tw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EducatieController {
    public void create(int  luna, String judet, int faraStudii, int primar, int gimnaziu, int liceu, int postliceala, int profesionala, int universitate) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement=connection.createStatement();
        String sql="insert into educatie values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        String lunaJudet=luna+judet;
        preparedStatement.setObject(1,luna );
        preparedStatement.setObject(2,judet );
        preparedStatement.setObject(3, faraStudii);
        preparedStatement.setObject(4, primar);
        preparedStatement.setObject(5,gimnaziu);
        preparedStatement.setObject(6,liceu);
        preparedStatement.setObject(7,postliceala);
        preparedStatement.setObject(8,profesionala);
        preparedStatement.setObject(9,universitate);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
    //    connection.close();
    }
}
