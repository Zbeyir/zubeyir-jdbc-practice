package jdbstests;

import org.junit.jupiter.api.Test;
import utilities.DBUtilities;

import java.util.List;
import java.util.Map;

public class DBUtilPractice {

    @Test
    public void test1() {

        //create connection
        DBUtilities.createConnection();

        String query = "select EMPLOYEE_ID,FIRST_NAME,LAST_NAME,SALARY\n" +
                "from EMPLOYEES\n" +
                "where ROWNUM <6";

        List<Map<String, Object>> queryData = DBUtilities.getQueryResultMap(query);

        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        //close the connection
        DBUtilities.destroy();
    }




    @Test
    public void test2() {

        //create connection
        DBUtilities.createConnection();

        String query = "select EMPLOYEE_ID,FIRST_NAME,LAST_NAME,SALARY\n" +
                "from EMPLOYEES\n" +
                "where ROWNUM <2";

        Map<String, Object> rowMap = DBUtilities.getRowMap(query); // bura da 1 row var onun icin direkt böyle yazdik

        //print the result
        System.out.println(rowMap.toString());  // bura da 1 row var onun icin direkt böyle yazdik

        //close the connection
        DBUtilities.destroy();
    }



}
