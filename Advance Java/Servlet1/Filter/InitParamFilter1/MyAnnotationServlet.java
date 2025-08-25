// Required imports
import java.io.*;                                       // Input/Output classes jaise PrintWriter ke liye
import javax.servlet.*;                                 // Servlet interfaces jaise ServletException
import javax.servlet.http.*;                            // HttpServletRequest, HttpServletResponse ke liye
import javax.servlet.annotation.WebServlet;             // Annotation @WebServlet ke liye

// WebServlet annotation ke through servlet define kiya gaya hai
// "name" servlet ka naam hai (optional)
// "urlPatterns" me define kiya gaya hai ki ye servlet "/hello" URL par chalega
@WebServlet(name = "MyAnnotationServlet", urlPatterns = {"/hello"})

// Servlet class banayi gayi hai jo HttpServlet ko extend karti hai
public class MyAnnotationServlet extends HttpServlet {

    // service() method ko override kiya gaya hai
    // Ye method client request aane par automatically call hota hai
    public void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // Response ka content type HTML set kiya gaya
        response.setContentType("text/html");

        // Response writer object banaya gaya jisse browser me output bhej sake
        PrintWriter out = response.getWriter();

        // Output HTML content client ko bhejna
        out.println("<h2>Hello World Servlet Annotation Example</h2>");
    }
}
