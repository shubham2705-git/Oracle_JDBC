import java.sql.*;
import javax.servlet.*;

public class MyDBListener implements ServletContextListener
{
	private Connection conn;
    public void contextInitialized(ServletContextEvent ev)
	{
		ServletContext ctxt = ev.getServletContext();
		String connurl=ctxt.getInitParameter("connurl");
		String dbusername=ctxt.getInitParameter("dbusername");
		String pwd=ctxt.getInitParameter("dbpwd");
        try {
            conn = DriverManager.getConnection(connurl,dbusername,pwd);
            System.out.println("Connected to the DB");
			ctxt.setAttribute("dbconn",conn);
        }catch (Exception ex) {
			System.out.println("Some problem in Listener: " + ex);
        }
    } 
    public void contextDestroyed(ServletContextEvent ev){
        try {
            if(conn!=null){
				conn.close();
			}
        }catch (Exception ex) {
			System.out.println("Exception in closing:"+ex);
        }
    }
}
