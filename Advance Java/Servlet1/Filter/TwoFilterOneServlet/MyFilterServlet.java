import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class MyFilterServlet  extends HttpServlet
{
	PrintWriter out;
	public void  service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		out= res.getWriter();
		
		ServletContext ctx= getServletContext();
		Integer count =(Integer)ctx.getAttribute("count");
		
		out.println("via Servlet="+count.intValue());
		out.println("<br>");
	}
}

/*
1–3. imports — servlet & IO classes.

public class MyFilterServlet extends HttpServlet

Ye ek servlet hai; HttpServlet extend kar raha hai.

PrintWriter out;

Instance variable me PrintWriter store kiya gaya. Caution: servlet instances multi-threaded hote hain (container ek instance banata hai aur multiple threads same instance pe service() chalate hain). Instance variables that hold request-specific objects (like PrintWriter) are not thread-safe. Better use local variable inside service().

public void service(HttpServletRequest req, HttpServletResponse res)

service() override karta hai jo GET/POST dono handle karta hai. Usually recommended to override doGet/doPost instead of service unless specifically needed.

res.setContentType("text/html");

Response ka MIME type set kar rahe ho — correct.

out = res.getWriter();

Writer assign kar rahe ho. (Again, local variable recommended.)

ServletContext ctx = getServletContext();

Application context le rahe ho.

Integer count = (Integer) ctx.getAttribute("count");

Attribute read kar rahe ho. Possible null — agar filter value set na kare ho to NPE aa sakta hai.

out.println("via Servlet="+count.intValue());

Prints the count. But agar count == null to yeh count.intValue() NullPointerException throw karega. Better: count == null ? 0 : count.

out.println("<br>"); — newline.

Behavioral notes:

Fixes needed: make PrintWriter local, null-check count, or better use AtomicInteger.

No chain here — servlet is endpoint.

Overall request flow & filter-order note
Filter execution order depends on web.xml filter-mapping order. Suppose mapping order is:

MyFilter1 -> /*

MyFilter -> /*
Then sequence for one request:

MyFilter1.doFilter() pre prints "MyFilter1 via jate hua"

chain.doFilter() → enters MyFilter.doFilter():

MyFilter currently does not call chain.doFilter() → request stops here. Servlet won't run.

If MyFilter CALLS chain.doFilter(), then servlet runs and after servlet returns control goes back: MyFilter post (if any), then back to MyFilter1 post (prints "MyFilter1 response aate hua").

Conclusion: Every filter that should allow the request to proceed must call chain.doFilter(req,res).

*/