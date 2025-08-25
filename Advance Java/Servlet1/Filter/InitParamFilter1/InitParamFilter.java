// Required imports
import java.io.*;                                   // Input-output ke liye
import javax.servlet.*;                             // Servlet API ke liye
import javax.servlet.annotation.*;                  // Annotations jaise @WebFilter ke liye

// WebFilter annotation se filter define kiya gaya hai
// "urlPatterns" batata hai ki filter "/hello" URL par kaam karega
// "initParams" se initialization parameter diya gaya hai
@WebFilter(urlPatterns={"/hello"},
    initParams={ @WebInitParam(name="simpleParam", value="paramValue") })

// Filter class banayi gayi hai jo Filter interface ko implement karti hai
public class InitParamFilter implements Filter {

    // FilterConfig object banaya jisse init parameters ko access kiya jata hai
    private FilterConfig filterConfig = null;

    // doFilter method har request par chalta hai
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // Init parameter "simpleParam" ka value get kiya gaya
        String s = filterConfig.getInitParameter("simpleParam");

        // Response me output diya gaya
        response.getWriter().println("Myfilter1 " + s);

        // Request ko next filter ya servlet me bhejne ke liye
        chain.doFilter(request, response);
    }

    // Filter ka init method, jo sirf ek baar chalta hai jab filter initialize hota hai
    // Isme FilterConfig object ko local variable me store kiya gaya
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    // destroy method tab chalta hai jab filter ko destroy kiya jata hai
    @Override
    public void destroy() {
        // Cleanup ka kaam yahan hota hai (agar zarurat ho)
    }
}
