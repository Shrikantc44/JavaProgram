import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class ListenerServlet extends HttpServlet
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        
        ServletContext ctx = getServletContext();
        Connection c = (Connection) ctx.getAttribute("con");
        
        try
        {
            Statement st = c.createStatement();
            ResultSet result1 = st.executeQuery("select * from emp101");

            while(result1.next())
            {
                out.println("id: " + result1.getString(1));
            }
        }
        catch(Exception e)
        {
            out.println("Error: " + e.getMessage());
        }

        out.println("</body></html>");
    }
}
