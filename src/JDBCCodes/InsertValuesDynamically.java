package JDBCCodes;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertValuesDynamically {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter emp id");
        int id = kb.nextInt();
        
        System.out.println("employee name");
        String name =  kb.next();
        
        System.out.println("Enter Salary");
        double amt = kb.nextDouble();
        
        System.out.println("Enter depno");
        int dno = kb.nextInt();
        
        try{
             conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
            Statement st = conn.createStatement();
            String query = "insert into employees values("+id+",'"+name+"',"+amt+","+dno+")";
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
