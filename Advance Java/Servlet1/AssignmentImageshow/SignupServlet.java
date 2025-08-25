import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.oreilly.servlet.MultipartRequest;

public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // File will be saved in temp folder
        String uploadPath = getServletContext().getRealPath("/") + "temp";
        File dir = new File(uploadPath);
        if (!dir.exists()) dir.mkdirs();

        MultipartRequest m = new MultipartRequest(request, uploadPath);

        String username = m.getParameter("username");
        String password = m.getParameter("password");

        File photoFile = m.getFile("photo");
        byte[] photoBytes = null;
        if (photoFile != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(photoFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            fis.close();
            photoBytes = baos.toByteArray();
        }

        UserDAO dao = new UserDAO();
        boolean success = dao.registerUser(username, password, photoBytes);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (success) {
            out.println("<h3>Registration Successful!</h3>");
            out.println("<a href='login.html'>Go to Login</a>");
        } else {
            out.println("<h3>Registration Failed</h3>");
            out.println("<a href='signup.html'>Try Again</a>");
        }
    }
}
