import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Encoder;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.getUser(username, password);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (user != null) {
            String base64Image = "";
            if (user.getPhoto() != null) {
                BASE64Encoder encoder = new BASE64Encoder();
                base64Image = encoder.encode(user.getPhoto());
            }
            out.println("<h2>Welcome, " + user.getUsername() + "</h2>");
            if (!base64Image.isEmpty()) {
                out.println("<img src='data:image/jpeg;base64," + base64Image + "' width='200' height='200'/>");
            }
        } else {
            out.println("<h3>Invalid credentials</h3>");
            out.println("<a href='login.html'>Try Again</a>");
        }
    }
}
