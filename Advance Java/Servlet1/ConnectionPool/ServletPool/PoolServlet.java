// Necessary imports for servlet, annotation, and JDBC functionality
import java.io.*;                           // For PrintWriter and IO operations
import javax.annotation.Resource;          // For @Resource annotation
import javax.servlet.*;                    // For generic servlet classes
import javax.servlet.annotation.WebServlet; // To declare servlet with annotations
import javax.servlet.http.*;               // For HttpServlet classes
import javax.sql.*;                        // For DataSource (Connection Pooling)
import java.sql.*;                         // For JDBC classes like Connection, Statement, etc.

@WebServlet(name = "PoolServlet", urlPatterns = {"/pool"})
// Declares the servlet with name "PoolServlet" and maps it to "/pool" URL
public class PoolServlet extends HttpServlet
{
    @Resource(mappedName = "tindi")
    // Injects the DataSource object from the server context using JNDI name "tindi"
    DataSource ds;  // ds will be used to get database connections

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        // Sets the content type of the response to "text/html"
        res.setContentType("text/html");

        // Get the PrintWriter object to write output to the browser
        PrintWriter out = res.getWriter();
        out.println("<html><body>");

        try
        {
            // Get a connection from the connection pool
            Connection c = ds.getConnection();

            // Create a Statement object from the connection
            Statement s = c.createStatement();

            // SQL query to fetch all rows from emp101 table
            String s1 = "select * from emp101";

            // Execute the query and get the result in ResultSet
            ResultSet rs = s.executeQuery(s1);

            // Loop through the result set
            while(rs.next())
            {
                // Print the first column value (assumed id)
                out.println("id: " + rs.getString(1));

                // Print the second column value (assumed name)
                out.println("name: " + rs.getString(2));

                // Add a line break in the output
                out.println("<br>");
            }

            // Close the HTML body and html tags
            out.println("</body></html>");
        }
        catch(Exception e)
        {
            // Catch and silently handle any exception (not recommended for production)
        }
    }
}
