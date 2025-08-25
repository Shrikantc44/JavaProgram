import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ErrorServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException , IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("Error Servlet");
		out.println("invalid username or password");
	}
}