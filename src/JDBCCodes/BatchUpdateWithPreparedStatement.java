
package JDBCCodes;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BatchUpdateWithPreparedStatement {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner kb = new Scanner(System.in);
        boolean error=false;
        try{
             conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
             PreparedStatement ps = conn.prepareStatement("insert into allbooks values(?,?,?)");
             conn.setAutoCommit(false);
             String choice;
             do{
                 System.out.println("Enter Bookid:");
                 int id = kb.nextInt();
                 
                 System.out.println("Enter Book name:");
                 kb.nextLine();
                 String bookname = kb.nextLine();
                 
                 System.out.println("Enter Book price:");
                 double amt = kb.nextDouble();
                 
                 ps.setInt(1,id);
                 ps.setString(2,bookname);
                 ps.setDouble(3,amt);
                 
                 ps.addBatch();
                 System.out.println("Do you want to continue(Yes/No)");
                 choice = kb.next();
             }while(choice.equalsIgnoreCase("Yes"));
             
             int[] arr = ps.executeBatch();
             for(int i=0; i<arr.length; i++){
                 int x=arr[i];
                 if(x==Statement.SUCCESS_NO_INFO)
                     System.out.println("Query no "+(i+1)+" effected unknown number of rows ");
                 else if(x==Statement.EXECUTE_FAILED)
                     System.out.println("Query no "+(i+1)+" generated minor exeption");
                 else
                    System.out.println("Query no "+(i+1)+ " affected "+x+" rows");
             }
        }catch(BatchUpdateException buex){
            int[] arr=buex.getUpdateCounts();
            System.out.println("Query no:" +(arr.length+1)+" generated exception");
            System.out.println("BatchUpdateException:"+buex.getMessage());
            error=true;
        }
        catch(SQLException ex){
            System.out.println("Exception in DB:"+ex.getMessage());
            error=true;
        }
        finally{
            try{
                if(conn!=null){
                if(!error){
                    conn.commit();    
                    System.out.println("Done! Records sent and Changes made!");
                }else{
                    conn.rollback();
                    System.out.println("Sorry! some querie in the batch failed,so we rolled back");
                }
                }
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
