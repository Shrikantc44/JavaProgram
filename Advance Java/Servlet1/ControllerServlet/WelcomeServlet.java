import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class WelcomeServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException , IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("Welcome Servlet");
	}
}