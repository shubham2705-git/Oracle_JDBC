package JDBCCodes;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteEmployees {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Enter depno");
        int dno = kb.nextInt();
        
        try{
             conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
            PreparedStatement ps = conn.prepareStatement("Delete from employees where deptno = ?");
            ps.setInt(1, dno);
            int ans;
            ans = ps.executeUpdate();
            if(ans==0)
            {
                System.out.println("No records Deleted");
            }
            else{
                System.out.println(ans +" records were Updated");
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
