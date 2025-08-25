import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import com.oreilly.servlet.MultipartRequest;

public class SignupServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String uploadPath = getServletContext().getRealPath("/") + "temp";
        File dir = new File(uploadPath);
        if (!dir.exists()) dir.mkdirs();

        MultipartRequest mpr = new MultipartRequest(req, uploadPath);

        String username = mpr.getParameter("username");
        String password = mpr.getParameter("password");

        File photoFile = mpr.getFile("photo");
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

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO userphoto (username, password, photo) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setBytes(3, photoBytes);

            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<h3>Registration Successful!</h3>");
                out.println("<a href='login.html'>Go to Login</a>");
            } else {
                out.println("<h3>Registration Failed!</h3>");
                out.println("<a href='signup.html'>Try Again</a>");
            }
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
    }
}
