package jdbstests;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        //String dbUrl = "jdbc:oracle:thin:your ID:1521:XE";
        String dbUrl = "jdbc:oracle:thin:@44.202.63.224:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        // burada önce  // su yukarida kinin cikmasi icin --import java.sql.*;--- signature yaptik
        //yani sag tus ve ilkine tikla
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");


        //once you set up connection default pointer looks for 0
        //next() --> move pointer to first row
        resultSet.next();

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));
                                        // burada columnLabel ---> in masi yani adi

        //ANOTHER OPTIONS--ANOTHER WAY--;:))
        //getting information with column index(starts 1) --> yani hacim bunu icine 0 yazarsan avcunu yalarsin hata alirsin
        System.out.println(resultSet.getInt(1));

        //ama eger assagidaki gibi yazrasak yine europ yazdiracak cünkü index(starts 1) den basliyor

        System.out.println(resultSet.getString(2));

        //1- Europe
        //2- Americas
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        //System.out.println(resultSet.getInt(2)+" - "+resultSet.getString(3));  // bu sekilde yazrsak hata aliriz

        //move to second row --> onun icin bunu kullaniyoruz
        resultSet.next();
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));

        //move to third row
        resultSet.next();
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));


        //close connection
        resultSet.close();
        statement.close();
        connection.close();








    }
}
