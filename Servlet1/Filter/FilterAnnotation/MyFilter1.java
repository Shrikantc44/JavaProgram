// Required imports
import javax.servlet.http.*;                         // HTTP request & response classes ke liye
import javax.servlet.*;                              // Servlet interfaces ke liye
import java.io.*;                                     // PrintWriter ke liye
import javax.servlet.annotation.*;                   // @WebFilter annotation ke liye

// Filter define kiya gaya hai annotation ke through
// Ye filter "/hello" URL par apply hoga, aur iska naam "MyFilter1" diya gaya hai
@WebFilter(urlPatterns = {"/hello"}, filterName = "MyFilter1")
public class MyFilter1 implements Filter {

    FilterConfig config; // Filter configuration object

    // init() method filter initialize hone par ek baar call hota hai
    public void init(FilterConfig config) throws ServletException {
        this.config = config; // config object ko local variable me store kiya
    }

    // doFilter() har request par chalti hai
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        // Response stream par output bhejna
        res.getWriter().println("filter2 "); // Client ko message dikhana: "filter2"

        // Request ko next filter ya servlet ko pass karna
        chain.doFilter(req, res);
    }

    // destroy() method jab filter destroy hota hai tab chalta hai
    public void destroy() {
        // Agar koi resource close karna ho to yahan likha jata hai
    }
}
