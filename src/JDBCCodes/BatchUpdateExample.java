
package JDBCCodes;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateExample {
    public static void main(String[] args) {
        Connection conn = null;
        boolean error=false;
        try{
             conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
             Statement st = conn.createStatement();
             conn.setAutoCommit(false);
             st.addBatch("INSERT INTO employees VALUES (111,'Shubham',55000.00,10,TO_DATE('30-AUG-2025','DD-MON-YYYY'))");
             st.addBatch("insert into allbooks values(107,'Mastering DA',980)");
             st.addBatch("Delete from products where prodid>=102");
             int[] arr = st.executeBatch();
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
