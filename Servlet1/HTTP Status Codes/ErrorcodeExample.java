import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ErrorcodeExample extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int x = 10 / 0;
        response.sendError(500);
    }
}
