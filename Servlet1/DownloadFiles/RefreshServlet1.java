import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class RefreshServlet1 extends HttpServlet 
{
    static int count=0;
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
	{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("Welcome to Codesquadz shrikant web");
        res.setHeader("Refresh","4;login.html");
    }
}
