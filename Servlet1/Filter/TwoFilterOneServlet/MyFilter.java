import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class MyFilter implements Filter
{
	FilterConfig config;
	public void init(FilterConfig config) throws ServletException
	{
		this.config=config;
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException
	{
		PrintWriter out=res.getWriter();
		ServletContext ctx=config.getServletContext();
		Integer count =(Integer)ctx.getAttribute("count");
		if(count==null)
		{
			count=new Integer(0);
		}
		count=new Integer(count.intValue()+1);
		ctx.setAttribute("count",count);
		out.println("myfilter jate hua");
		out.println("<br>");
	}
	public void destroy()
	{
	}
}
	
	
/*

Line-by-line explanation (MyFilter):

import javax.servlet.http.*;

HTTP-specific servlet classes (HttpServletRequest/Response etc.). Yaha do filter mein general ServletRequest use hua hai, par import safe hai.

import javax.servlet.*;

Filter, FilterConfig, FilterChain, ServletContext, ServletException — sab milta hai.

import java.io.*;

PrintWriter ke liye.

public class MyFilter implements Filter

Ye class Filter interface implement karti hai. Filter ko container call karta hai.

FilterConfig config;

Instance variable: filter init parameters aur getServletContext() ke liye stored config.

8–11. init(FilterConfig config)

Container jab filter load karega tab init() call hota hai. Yaha received FilterConfig ko instance variable me store kiya ja raha hai.

public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)

Har request pe container ye method call karta hai. req/res generic interfaces hain. chain se aage next filter/servlet ko call karte hain.

PrintWriter out=res.getWriter();

Response writer le rahe ho. Note: Agar response ka type set nahi kiya gaya (e.g. text/html) to browser behavior depend karega. Also getWriter() call karna response ko commit kar sakta hai agar buffer fill ho jaye.

ServletContext ctx=config.getServletContext();

Application-wide storage (attributes) ke liye context le rahe ho.

Integer count =(Integer)ctx.getAttribute("count");

ServletContext se "count" attribute read kar rahe ho (visit counter).

17–20. if(count==null){ count=new Integer(0); }

Agar pehli baar null hai to 0 se initialize kar rahe ho.

count=new Integer(count.intValue()+1);

Purana value + 1 kar ke new Integer banaya. (old style — auto-boxing better hota hai)

ctx.setAttribute("count",count);

Updated counter dobara ServletContext me set kar rahe ho.

out.println("myfilter jate hua");

Response me ek message print kar rahe ho (pre-processing message).

out.println("<br>");

New line HTML ke liye.

26–28. destroy()

Container unload karte time cleanup. Empty — ok.

Behavioral note (important):

chain.doFilter(req,res) missing hai. Matlab filter request ko aage (next filter/servlet) forward nahin kar raha — isse request yahin ruk jayegi aur servlet kabhi call nahi hoga. Agar intention yahi hai to fine, lekin aam case me har filter ko chain.doFilter(...) call karna chahiye (pre-processing → chain.doFilter → post-processing).

Counter increment thread-safe nahi hai: multiple concurrent requests race condition bana sakte hain.

new Integer(...) outdated; use Integer.valueOf(...) ya AtomicInteger.

*/
		