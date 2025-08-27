package JDBCCodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class StoringImageInColumn {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Enter path to the image");
        String imgPath = kb.nextLine();
        File imgFile = new File(imgPath);
        try{
            FileInputStream fin = new FileInputStream(imgPath);
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//Shubham-Rj:1521/xe","advjavabatch2","mystudents");
            PreparedStatement ps = conn.prepareStatement("Insert into allmovies values(?,?)");
            ps.setBinaryStream(2,fin,(int)imgFile.length());
            // type casting because the argument type of the method .setBinaryStream() is int and return type of .length() method is long --
            int indexOfFwdS1 = imgPath.lastIndexOf("/");
            int lastDotPos = imgPath.lastIndexOf("."); 
            String movieName = imgPath.substring(indexOfFwdS1+1,lastDotPos);
            ps.setString(1,movieName);
            int ans = ps.executeUpdate();
            System.out.println("Rec inserted:"+ans);
            
        }catch(FileNotFoundException fnf){
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
