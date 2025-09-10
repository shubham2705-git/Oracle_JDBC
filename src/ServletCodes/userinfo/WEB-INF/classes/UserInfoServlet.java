import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;
public class UserInfoServlet extends HttpServlet{
     
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
 { 
 resp.setContentType("text/html");
PrintWriter pw = resp.getWriter();
pw.println("<html>");
pw.println("<head><title>Your Details</title>");

pw.println("</head><body>");
String name = req.getParameter("username");
String gen = req.getParameter("gender");
String[] hobbies=req.getParameterValues("hobbies");
String str="";
for(String hobby: hobbies){
   str+=hobby+","; 
 }
pw.println("<b>Your username:</b>" +name+"<br>");
pw.println("<b>Your gender:</b>" +gen+"<br>");
pw.println("<b>Your hobbies:</b>" +str.substring(0,str.length()-1)+"<br>");
   
pw.println("</body>");
pw.println("</html>");
pw.close();
}
    }
