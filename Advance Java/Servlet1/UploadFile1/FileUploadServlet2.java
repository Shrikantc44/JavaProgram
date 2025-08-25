import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import com.oreilly.servlet.*;

public class FileUploadServlet2 extends HttpServlet 
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try 
		{
            res.setContentType("text/html");
            ServletInputStream in = req.getInputStream();
            ServletOutputStream out = res.getOutputStream();

            FileOutputStream fout = new FileOutputStream("D:\\f2\\demo17.txt");

            int i = 0;
            while ((i = in.read()) != -1) 
			{
                fout.write(i);
            }

            out.println("file uploaded");
        } 
		catch (Exception e) 
		{
            // Handle the exception here if needed
        }
    }
}
