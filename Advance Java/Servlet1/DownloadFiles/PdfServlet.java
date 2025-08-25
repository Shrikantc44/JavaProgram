import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class PdfServlet extends HttpServlet 
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException 
    {
        String fileName = "daywise.pdf";
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        // PrintWriter pw = response.getWriter();

        try 
		{
            stream = response.getOutputStream();

            String s1 = getServletContext().getRealPath("/files/daywise.pdf");
            File doc = new File(s1);

            response.setContentType("application/pdf");

            response.addHeader(
                "Content-Disposition","attachment;filename=" + fileName );

            response.setContentLength((int) doc.length());

            FileInputStream input = new FileInputStream(doc);
            buf = new BufferedInputStream(input);
            int readBytes = 0;

            while ((readBytes = buf.read()) != -1)
                stream.write(readBytes);
        } 
		catch (Exception e) { }
    }
}
