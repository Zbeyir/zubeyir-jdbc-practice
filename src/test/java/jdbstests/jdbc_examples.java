package jdbstests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@44.202.63.224:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //move to first row
       // resultSet.next();

      //  System.out.println(resultSet.getString(2));

        //display departments table in 10 - Administrations - 200 - 1700 format

        //code for iterating for each row
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2)+
                    " - "+ resultSet.getString(3)+" - "+ resultSet.getInt(4));
        }
//3.row u string yaptik cünkü 0 yazmasini istemiyoruz bilakis null yazmasini istedigimiz icin

        System.out.println("===================================================");

        resultSet = statement.executeQuery("SELECT * FROM regions");
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        }



        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }




    @DisplayName("ResultSet Methods")  // istege bagli sol taraf ta assagi run edince görüküyr test ler de
    @Test
    public void test2() throws  SQLException{

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //BURADA SADECE bu yukarida ki line 'i degistirdik--->cünkü
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");


        //how to find how many rows we have for the query
        //move to last row
        resultSet.last(); //-->cünkü--> bura o yukariyi degistirmeden hata veriyor

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);



        //to move before first row after we use .last method
        resultSet.beforeFirst();
        //eger bunu yazmasa idik assagidaki alamiyacaktik, last tan sonra bir sey istiyorsak bunu yazacagiz

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }





        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }




    @Test
    public void test3() throws  SQLException{

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");


        //get the database related data inside the dbMetedata object
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());


        //get the resultsetmetadata //rsmd
        ResultSetMetaData rsMetaData = resultSet.getMetaData();


        //how many columns we have ?
        int colCount = rsMetaData.getColumnCount();
        System.out.println(colCount);


        //getting column names
        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getColumnName(2));



        //rsMetadata.getColumnName(i) --> gets column name
        //rsMetadata.getColumnCount() --> total number of columns
        //print all the column names dynamically

        //bunun icine i=0 yazdim haat aldim , cünkü index baslangic 1 ulaaaa haci 1  :)))
        // ben  'colCount' --> yerine 11 sonucu yazdim bu dynamic degil
        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsMetaData.getColumnName(i));
        }




        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }



}
