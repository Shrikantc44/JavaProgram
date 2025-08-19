import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ExcelServlet extends HttpServlet
{
    PrintWriter out;
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-Disposition", "attachment; filename=excel.xls");
        out = res.getWriter();
        out.println("names\thindi\teng\tmaths\ttotal");
        out.println("lalu\t 35\t 20\t 95\t=sum(b2:d2)");
        out.println("prince\t 80\t 82\t 95\t=sum(b3:d3)");
    }
}
