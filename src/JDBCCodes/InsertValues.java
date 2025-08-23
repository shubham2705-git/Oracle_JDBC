
package JDBCCodes;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertValues {
    public static void main(String[] args) {
        Connection conn = null;
        try{
             conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
            Statement st = conn.createStatement();
            String query = "insert into employees values(108,'Jayant',40000,30)";
            int res;
            res = st.executeUpdate(query);
            System.out.println("No of rows Affected:" +res);
        }catch(SQLException ex){
            System.out.println("Exception in DB:"+ex.getMessage());
        }
        finally{
            try{
                if(conn!=null){
                    conn.close();
                System.out.println("Disconnect successfully to the DB");
                }
            }catch(SQLException ex){
             System.out.println("Exception in closing the connection:"+ex.getMessage());  
            }
       }
    }
}
