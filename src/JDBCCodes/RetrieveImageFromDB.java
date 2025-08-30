package JDBCCodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetrieveImageFromDB {
    public static void main(String[] args) {
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("Select * from allmovies");
            File f = new File("d:/shubham");
            f.mkdir();
            while(rs.next()){
                String mname = rs.getString(1)+".png";
                Blob obj = rs.getBlob(2);
                byte[] arr = obj.getBytes(1,(int)obj.length());
                FileOutputStream fout = new FileOutputStream("d:/shubham/"+mname);
                fout.write(arr);
                fout.close();
                
                System.out.println("Saved:"+mname);
            }
            
        }catch(IOException fnf){
            System.out.println("File not present:"+fnf.getMessage());
        }
        
        catch(SQLException ex){
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
