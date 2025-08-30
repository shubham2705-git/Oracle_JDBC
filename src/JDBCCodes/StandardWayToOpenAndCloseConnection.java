
package JDBCCodes;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StandardWayToOpenAndCloseConnection {
    public static void main(String[] args) {
        Connection conn = null;
        try{
             conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
            System.out.println("Connected to the DB");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select ename,sal from employees where deptno=30");
            while(rs.next())
            {
                String ename = rs.getString(1);
                double sal = rs.getDouble(2);
                System.out.println(ename+"\t"+sal);
            }
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
