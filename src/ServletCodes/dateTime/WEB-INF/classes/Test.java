import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet{
     
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
 { 
 resp.setContentType("text/html");
 resp.setCharacterEncoding("UTF-8");

PrintWriter pw = resp.getWriter();

pw.println("<!DOCTYPE html><html>");
pw.println("<head>");
pw.println("<title>Test</title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<h2>Hello World</h2>");
pw.println("</body>");
pw.println("</html>");
}
    }
