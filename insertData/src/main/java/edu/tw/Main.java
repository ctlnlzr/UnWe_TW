package edu.tw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Database.getConnection();
        connection.setAutoCommit(false);
        EducatieController educatie = new EducatieController();
        VarstaController varsta = new VarstaController();
        MediuController mediu=new MediuController();
        TotalPerLunaController total=new TotalPerLunaController();
        BufferedReader line = new BufferedReader(new FileReader("C:\\Users\\strat\\Desktop\\rata\\rata15.csv"));
        line.readLine(); //fara prima linie;
        String lineText = null;
      while ((lineText = line.readLine()) != null) {
            String[] data = lineText.split(","); //pentru varsta
            String judet = data[0];
            System.out.println(judet);
            String[] data3 = lineText.split("\", ");
            String[] data2 = lineText.split("\"");
           // String maiMic25 = data[11];
           // StringTokenizer token = new StringTokenizer(maiMic25, " ");
            StringTokenizer token2 = new StringTokenizer(data3[1], ",");
            String maiMic29 = data2[1];
            String corect=maiMic29.replace(",", "");
            String corect2=corect.replace(" ", "");
           total.create(15, judet,Integer.parseInt(corect2),  Float.parseFloat(token2.nextToken()));


        }
        line.close();
     // total.create(15, "ILFOV", 776, (float) 0.39);
    }
}

