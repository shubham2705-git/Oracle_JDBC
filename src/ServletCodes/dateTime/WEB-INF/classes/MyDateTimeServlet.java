import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
public class MyDateTimeServlet extends HttpServlet{
     
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
 { 
 resp.setContentType("text/html");
PrintWriter pw = resp.getWriter();
pw.println("<html>");
pw.println("<head><title>My Date Time Servlet</title>");
pw.println("<style>h2{color:red;}</style></head>");
pw.println("<body>");
Date today = new Date();
pw.println("<h2>Currrent Date and time at the server is : "+today+"</h2>");
pw.println("</body>");
pw.println("</html>");
pw.close();
}
    }
