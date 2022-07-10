package jdbstests;

import java.sql.*;

public class TestConnectionsEasyWay {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@44.202.63.224:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");


        while (resultSet.next()) {

            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        }


        resultSet.close();
        statement.close();
        connection.close();


    }
}
