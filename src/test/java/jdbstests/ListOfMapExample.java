package jdbstests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapExample {

    String dbUrl = "jdbc:oracle:thin:@44.202.63.224:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() {

        //creating list for keeping all the rows maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();
        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRES");

        System.out.println(row1.toString());


        Map<String,Object> row2 = new HashMap<>();
        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");
        System.out.println(row2.toString());

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);


        //get the steven lastname directly from the list
        System.out.println(queryData.get(0).get("last_name"));
        //ilkine 0 yazdik cünkü büyükList<> icinde ilk sirada, 2. si ne de key adini yazdik


        System.out.println(queryData.get(1).get("salary"));
        //mesela bak bu da Büyük List<> 2. sirada kinin maasi


    }



    @Test
    public void test2() throws SQLException{

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select EMPLOYEE_ID,FIRST_NAME,LAST_NAME,SALARY\n" +
                "from EMPLOYEES\n" +
                "where ROWNUM <6");

     //burada dikkat edilecek önemli sey parantez icinden ; noktali virgülü sil copy paste yaparken



        //move to first row
        resultSet.next();


        //in order to get column names we need resultsetmetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();


        //creating list for keeping all the rows maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();
        row1.put(rsmd.getColumnName(1),resultSet.getString(1));
        row1.put(rsmd.getColumnName(2),resultSet.getString(2));
        row1.put(rsmd.getColumnName(3),resultSet.getString(3));
        row1.put(rsmd.getColumnName(4),resultSet.getString(4));


        System.out.println(row1.toString());


        //move to first row
        resultSet.next();  //---> bunu yazmaz isek alta ki sonucu alamayiz

        Map<String,Object> row2 = new HashMap<>();

        row2.put(rsmd.getColumnName(1),resultSet.getString(1));
        row2.put(rsmd.getColumnName(2),resultSet.getString(2));
        row2.put(rsmd.getColumnName(3),resultSet.getString(3));
        row2.put(rsmd.getColumnName(4),resultSet.getString(4));

        System.out.println(row2.toString());

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);




        resultSet.close();
        statement.close();
        connection.close();

    }











}
