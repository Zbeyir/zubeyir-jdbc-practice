package jdbstests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

public class dynamic_list {


    String dbUrl = "jdbc:oracle:thin:@44.202.63.224:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select EMPLOYEE_ID,FIRST_NAME,LAST_NAME,SALARY\n" +
                "from EMPLOYEES\n" +
                "where ROWNUM <6");

        // sadece "bunun icini" degistirdigimizde sonuc degisecek iste bu dynamic
        //yani "select * from EMPLOYEES" yazarsak employees 'in sonucunu alacagiz


        //in order to get column names we need resultsetmetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();


        //list of maps to keep all information
        List<Map<String, Object>> queryData = new ArrayList<>();


        //number of columns
        int colCount = rsmd.getColumnCount();

        //loop through each row
        while (resultSet.next()) {

            Map<String, Object> row = new LinkedHashMap<>();

            //some code to fill the dynamically
            for (int i = 1; i <= colCount; i++) {

                row.put(rsmd.getColumnName(i),resultSet.getObject(i));

            }


            //add ready map row to the list
            queryData.add(row);

        }

        //print each row inside the list
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }


        resultSet.close();
        statement.close();
        connection.close();

    }


}


