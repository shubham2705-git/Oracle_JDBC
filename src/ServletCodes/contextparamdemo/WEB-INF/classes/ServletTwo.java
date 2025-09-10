import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletTwo extends HttpServlet {
    
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<head><title>context Parameter Demo</title></head>");
        pw.println("<body>");
		
        ServletContext ctxt = getServletContext();
		String emailid=ctxt.getInitParameter("emailid");
		String phone=ctxt.getInitParameter("phoneno");
		
		pw.println("<h2>Contact Details</h2>");
		pw.println("<strong> Our emailid:</strong>"+emailid);
		pw.println("<strong> Our contact no:</strong>"+phone);
		
		
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }
   
}
