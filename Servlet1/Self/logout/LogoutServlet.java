import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the current session (don't create a new one if it doesn't exist)
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Invalidate the session
            session.invalidate();
        }

        // Redirect user to login page
        response.sendRedirect("login.jsp");
    }
}
