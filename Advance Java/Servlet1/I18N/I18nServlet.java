import javax.servlet.http.*;        // HTTP-specific servlet classes ke liye
import javax.servlet.*;             // General servlet classes ke liye
import java.io.*;                   // Input/Output stream ke liye
import java.util.*;                 // Locale, ResourceBundle, Enumeration ke liye
import java.sql.*;                  // (Unused hai abhi) DB ke liye

public class I18nServlet extends HttpServlet {
    
    // Servlet ka main method jahan request aata hai aur response diya jata hai
    public void service(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException {
        
        String t = null;         // Title ke liye
        String name = null;      // Username label ke liye
        String pass = null;      // Password label ke liye

        res.setContentType("text/html");   // Response ka type HTML set kiya
        PrintWriter out = res.getWriter(); // Output stream le liya for writing HTML

        Locale l = req.getLocale();  // Client (browser) ki locale get ki

        // ResourceBundle ke through correct language ki file load karna
        ResourceBundle rb = ResourceBundle.getBundle("ResourceBundle", l);

        // Properties file ke sabhi keys ko lene ke liye Enumeration
        Enumeration e = rb.getKeys();

        while (e.hasMoreElements()) {         // Jab tak keys available hain
            String k = (String) e.nextElement();   // Next key lo

            if (k.equals("app.title")) {          // Agar key app.title hai
                t = rb.getString(k);              // To value t me store karo
            }

            if (k.equals("app.username")) {       // Agar key app.username hai
                name = rb.getString(k);           // To value name me store karo
            }

            if (k.equals("app.password")) {       // Agar key app.password hai
                pass = rb.getString(k);           // To value pass me store karo
            }
        }

        // HTML form ka output banana start
        out.println("<html>");
        out.println("<body>");
        
        // Title (heading) print karo
        out.println("<h1 style='color:blue'>" + t + "</h1>");
        out.println("<br>");

        // Form start karo
        out.println("<form action='login'>");

        // Username label print karo
        out.println(name + " ");
        out.println("<input type='text' name='uname'>");
        out.println("<br>");

        // Password label print karo
        out.println(pass + " ");
        out.println("<input type='text' name='upass'>");
        out.println("<br>");

        // Submit button
        out.println("<input type='submit'>");

        // Form close
        out.println("</form>");
        out.println("</body></html>");
    }
}
