import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;

public class Mp3Servlet extends HttpServlet 
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException 
    {
        String fileName = "girlineedyou.mp3";
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        
        stream = response.getOutputStream();
        String s1 = getServletContext().getRealPath("/files/girlineedyou.mp3");
        File doc = new File(s1);

        response.setContentType("audio/mpeg");

        response.setHeader("ContentDisposition", "attachment; filename=" + fileName);

        response.setContentLength((int) doc.length());

        FileInputStream input = new FileInputStream(doc);
        buf = new BufferedInputStream(input);
        int readBytes = 0;

        while ((readBytes = buf.read()) != -1)
            stream.write(readBytes);
    }
}
