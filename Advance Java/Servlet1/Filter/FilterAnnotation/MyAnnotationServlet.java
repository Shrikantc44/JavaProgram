// Required imports
import java.io.*;                                      // For PrintWriter and I/O
import javax.servlet.*;                                // For Servlet interfaces
import javax.servlet.http.*;                           // For HttpServletRequest & HttpServletResponse
import javax.servlet.annotation.WebServlet;            // For @WebServlet annotation

// Servlet annotation: Servlet ko "/hello" URL se map kiya gaya hai
@WebServlet(name = "MyAnnotationServlet", urlPatterns = {"/hello"})
public class MyAnnotationServlet extends HttpServlet {

    // service() method client ki request handle karta hai
    public void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Response ka content type set kiya gaya HTML ke liye
        response.setContentType("text/html");

        // Output stream le rahe hain response ke liye
        PrintWriter out = response.getWriter();

        // ServletContext use kar rahe hain context-wide data access ke liye
        ServletContext ctx = getServletContext();

        // Context attribute "count" ko retrieve kiya gaya
        Integer count = (Integer) ctx.getAttribute("count");

        // Output me HTML format me message + count print kar rahe hain
        out.println("<h2>Hello World Servlet Annotation Example</h2> " + count);
    }
}
