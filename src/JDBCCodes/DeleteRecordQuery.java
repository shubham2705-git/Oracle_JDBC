package JDBCCodes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteRecordQuery {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Establish connection
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//Shubham-Rj:1521/xe",
                    "advjavabatch2",
                    "mystudents");

            Statement st = conn.createStatement();
            
            // Delete employees from dept 10
            String query = "DELETE FROM employees WHERE deptno = 10";
            int res = st.executeUpdate(query);

            if (res > 0) {
                System.out.println("Total records deleted: " + res);
            } else {
                System.out.println("No record of dep 10 found in the database");
            }

        } catch (SQLException ex) {
            System.out.println("Exception in DB: " + ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Disconnected successfully from the DB");
                }
            } catch (SQLException ex) {
                System.out.println("Exception in closing the connection: " + ex.getMessage());
            }
        }
    }
}
