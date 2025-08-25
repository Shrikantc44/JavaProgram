import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class OracleServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        res.setContentType("text/html");
        ServletContext ctx = getServletContext();
        PrintWriter out = res.getWriter();
        
        out.println("<html><body>");

        // Initialization Parameters
        String driver = ctx.getInitParameter("driver");
        String url = ctx.getInitParameter("url");
        String user = ctx.getInitParameter("user");
        String pass = ctx.getInitParameter("pass");

        try {
            // Load JDBC Driver
            Class.forName(driver);

            // Establish Connection
            Connection c = DriverManager.getConnection(url, user, pass);

            // Create Statement & Execute Query
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM emp101");

            // Display Data
            while (rs.next()) {
                out.println(rs.getString(1));
                out.println(rs.getString(2));
                out.println("<br>");
            }

            // Close Resources
            rs.close();
            s.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        out.println("</body></html>");
    }
}
