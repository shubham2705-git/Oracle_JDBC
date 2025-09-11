import java.io.*;
import javax.servlet.*;

public class MyListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent ev){
        try {
            System.out.println("contextInitialized called...");
            System.in.read();
        }catch (IOException ex) {
        }
    } 
    public void contextDestroyed(ServletContextEvent ev){
        try {
            System.out.println("contextDestroyed called...");
        }catch (IOException e) {
        }
    }
}
