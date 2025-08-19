import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogFilter implements Filter {
    private FilterConfig filterConfig = null;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        long start = System.currentTimeMillis();
        String address = request.getRemoteAddr();
        String file = ((HttpServletRequest) request).getRequestURI();
        filterConfig.getServletContext().log(
            "User access! " +
            "User IP: " + address +
            " Resource: " + file +
            " Milliseconds used: " + (System.currentTimeMillis() - start)
        );

        chain.doFilter(request, response);

        PrintWriter out = response.getWriter();
        out.println("log filter");
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
