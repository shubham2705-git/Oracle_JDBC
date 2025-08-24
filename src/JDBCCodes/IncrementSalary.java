package JDBCCodes;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IncrementSalary {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner kb = new Scanner(System.in);
        
        
        System.out.println("Enter the Increment amount");
        double amt = kb.nextDouble();
        
        System.out.println("Enter depno");
        int dno = kb.nextInt();
        
        try{
             conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
            PreparedStatement ps = conn.prepareStatement("Update employees set sal=sal+? where deptno = ?");
            ps.setDouble(1, amt);
            ps.setInt(2, dno);
            int ans;
            ans = ps.executeUpdate();
            if(ans==0)
            {
                System.out.println("No records updated");
            }
            else{
                System.out.println(ans +" Records Updated");
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
