import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;

public class LoginServlet2 extends HttpServlet {
    private Connection conn;
    private PreparedStatement ps;
    public void init() throws ServletException {
        try {
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@//Shubham-Rj:1521/xe",
                "advjavabatch2",
                "mystudents"
            );
            System.out.println("Connected to the DB");

            ps = conn.prepareStatement(
                "select username from users where userid=? and password=?"
            );
        } catch (SQLException sq) {
            System.out.println("Some problem in init: " + sq);
            ServletException obj = new ServletException(sq.getMessage());
            throw obj;
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<head><title>Login Response</title></head>");
        pw.println("<body>");
        String userid = req.getParameter("userid");
        String pwd = req.getParameter("password");
        try {
            ps.setString(1, userid);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString(1);
                pw.println("<h2>Welcome " + username + "</h2>");
                pw.println("<p>Login Successful. Enjoy surfing!</p>");
            } else {
                pw.println("<h2>Sorry !</h2>");
                pw.println("<p>Login rejected. invalid userid/password!</p>");
                pw.println("<p>Try again <a href='signin.html'> Login again</a></p>");
                pw.println("<p>New user ? <a href='signup.html'> Register new user</a></p>");
            }
        } catch (SQLException sq) {
            System.out.println("Some problem in doPost: " + sq);
            pw.println("<h2>Sorry!</h2>");
            pw.println("<p>Server is experiencing some issues. Try later </p>");
        }
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }
    public void destroy() {
        try {
            conn.close();
        } catch (SQLException sq) {
            System.out.println("Some problem in closing the conn: " + sq);
        }
    }
}
