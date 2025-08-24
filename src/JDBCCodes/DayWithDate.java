
package JDBCCodes;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DayWithDate {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select ename,hiredate from employees");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY,EEEE");
            while(rs.next())
            {
                String ename = rs.getString(1);
                Date hd = rs.getDate(2);
                String dateStr = sdf.format(hd);
                if(dateStr.endsWith("Sunday") || dateStr.endsWith("Saturday"))
                    System.out.print("* ");
                System.out.println(ename+"\t"+dateStr);
            }
            
        }catch(SQLException ex){
            System.out.println("Exception in DB:"+ex.getMessage());
        }finally{
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
    
