import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // demo credentials (change as needed)
    private static final String DEMO_USER = "root";
    private static final String DEMO_PASS = "shrik";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (DEMO_USER.equals(username) && DEMO_PASS.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("index.html"); // logged in -> back to search
        } else {
            response.sendRedirect("error?message=Invalid username or password");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
