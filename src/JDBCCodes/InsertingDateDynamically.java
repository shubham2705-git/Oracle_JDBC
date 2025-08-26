package JDBCCodes;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertingDateDynamically {
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
        
        System.out.println("Enter hiredate(dd-mm-yyyy)");
        String dateStr = kb.next();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        
        try{
            java.util.Date d1 = sdf.parse(dateStr);
            long ms = d1.getTime();
            java.sql.Date d2 = new java.sql.Date(ms);
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
            PreparedStatement ps = conn.prepareStatement("Insert into employees values(?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, amt);
            ps.setInt(4, dno);
            ps.setDate(5, d2);
            int ans;
            ans = ps.executeUpdate();
            System.out.println("Record Inserted:"+ans);            
            
        }catch(SQLException ex){
            System.out.println("Exception in DB:"+ex.getMessage());
        }
        catch(ParseException ex){
            System.out.println("Exception in Parsing of Date:"+ex.getMessage());
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
