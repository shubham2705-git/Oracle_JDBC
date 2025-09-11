import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;

public class RegistrationServlet3 extends HttpServlet {
    private Connection conn;
    private PreparedStatement ps;
    public void init() throws ServletException {
        ServletContext ctxt=super.getServletContext();
		ServletConfig cfg=super.getServletConfig();
		String connurl=ctxt.getInitParameter("connurl");
		String dbusername=ctxt.getInitParameter("dbusername");
		String pwd=ctxt.getInitParameter("dbpwd");
		String qry=cfg.getInitParameter("qry"); 
        try {
            conn = DriverManager.getConnection(connurl,dbusername,pwd);
            System.out.println("Connected to the DB");
            ps = conn.prepareStatement(qry);
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
        pw.println("<head><title>Registration Response</title></head>");
        pw.println("<body>");
        String userid = req.getParameter("userid");
        String pwd = req.getParameter("password");
		String username = req.getParameter("username");
        try {
            ps.setString(1, userid);
            ps.setString(2, pwd);
            ps.setString(3,username);
            int ans = ps.executeUpdate();
            if (ans==1) {
                pw.println("<h2>Thank you for regestring with us " + username + "</h2>");
                pw.println("<p>now you can <a href='signin.html'> Login</a></p>");
            } else {
                pw.println("<h2>Sorry !</h2>");
                pw.println("<p>Registration failed</p>");
                pw.println("<p>Try again <a href='signin.html'> register again</a></p>");
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
