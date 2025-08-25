import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class MyFilter1 implements Filter
{
	FilterConfig config;
	public void init(FilterConfig config ) throws ServletException
	{
		this.config = config;
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException , IOException
	{
		res.getWriter().println("MyFilter1 via jate hua  ");
		res.getWriter().println("<br>");
		chain.doFilter(req,res);
		res.getWriter().println("<br>");
		res.getWriter().println("MyFilter1 response aate hua  ");
		res.getWriter().println("<br>");
	}
	public void destroy()
	{
	}
}
		
		
/*
1–3. imports — same reasons as pehle.

public class MyFilter1 implements Filter — naya filter.

FilterConfig config; — store karne ke liye.

8–11. init(...) — init me config set ho raha hai (abhi unused hai lekin theek).

doFilter(...) signature — same lifecycle method.

14–15. res.getWriter().println("MyFilter1 via jate hua "); and println("<br>");

Pre-processing: response me message likh rahe ho. Note: har baar res.getWriter() call karna acceptable hai (same PrintWriter return hota hai), lekin better practice hai PrintWriter out = res.getWriter(); ek baar le lo.

chain.doFilter(req,res);

Very important: yeh call aage ke filters / servlet ko execute karta hai. Iske bina request aage nahi jayega. Yeh call ke baad jo statements hain vo post-processing ke roop me chalenge (jab servlet/next filter complete ho jayega aur response wapas aa rahi hogi).

17–19. post-processing prints — yeh servlet ke response ke baad run honge. Agar servlet ne response already commit kar diya ho (e.g. buffer full) to yeh add karna possible hai lekin kuch cases me visible na ho ya response broken ho sakta hai.

21–23. destroy() — empty.

Behavioral notes:

Yeh filter correct pattern follow karta: pre → chain.doFilter → post.

Better to set content type before getWriter (res.setContentType("text/html")) — warna browser may not render HTML tags as expected.

*/	