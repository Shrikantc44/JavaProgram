// Required imports
import javax.servlet.http.*;                        // HttpServletRequest & Response ke liye
import javax.servlet.*;                             // Servlet API ke liye
import javax.servlet.annotation.*;                  // Annotation jaise @WebFilter ke liye
import java.io.*;                                   // Input/Output ke liye (PrintWriter)

// WebFilter annotation se filter define kiya gaya hai
// Ye filter "/hello" URL par apply hoga, aur iska naam "MyFilter" diya gaya hai
@WebFilter(urlPatterns = { "/hello" }, filterName = "MyFilter")
public class MyFilter implements Filter {

    FilterConfig config;  // FilterConfig object to access context, init params etc.

    // init() method filter initialization ke liye hota hai (1 baar chalta hai)
    public void init(FilterConfig config) throws ServletException {
        this.config = config;  // FilterConfig ko local variable me store karna
    }

    // doFilter() har request ke liye chalta hai
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        // Response stream ke liye PrintWriter object banaya
        PrintWriter out = res.getWriter();

        out.println("MyFilter");  // Pehle message print hoga

        // ServletContext ko access kiya via FilterConfig
        ServletContext ctx = config.getServletContext();

        // Context attribute "count" ko get kiya gaya
        Integer count = (Integer) ctx.getAttribute("count");

        // Agar count null hai to use 0 se initialize karo
        if (count == null)
            count = new Integer(0);

        // Count increment karo (har request par)
        count = new Integer(count.intValue() + 1);

        // Updated count ko context me set karo
        ctx.setAttribute("count", count);

        // Filter chain continue karo (next filter ya servlet ko request pass karo)
        chain.doFilter(req, res);

        out.println("filter output");  // Chain ke baad message print hoga
    }

    // destroy() method jab filter destroy hota hai tab chalta hai
    public void destroy() {
        // Clean-up code agar kuch likhna ho to yahan likhte hain
    }
}
