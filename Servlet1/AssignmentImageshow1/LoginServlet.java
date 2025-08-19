import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    // Custom Base64 encode method (pure Java, no external lib)
    private static String base64Encode(byte[] bytes) {
        final char[] base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        StringBuilder sb = new StringBuilder();
        int paddingCount = (3 - (bytes.length % 3)) % 3;
        int length = bytes.length;
        for (int i = 0; i < length; i += 3) {
            int b = ((bytes[i] & 0xFF) << 16) & 0xFFFFFF;
            if (i + 1 < length) b |= (bytes[i + 1] & 0xFF) << 8;
            if (i + 2 < length) b |= (bytes[i + 2] & 0xFF);
            for (int j = 0; j < 4; j++) {
                int c = (b & 0xFC0000) >> 18;
                sb.append(base64Chars[c]);
                b <<= 6;
            }
        }
        for (int i = 0; i < paddingCount; i++) {
            sb.setCharAt(sb.length() - 1 - i, '=');
        }
        return sb.toString();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM userphoto WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                byte[] imgBytes = rs.getBytes("photo");
                String base64Image = "";
                if (imgBytes != null) {
                    base64Image = base64Encode(imgBytes);
                }
                out.println("<h2>Welcome, " + rs.getString("username") + "</h2>");
                if (!base64Image.isEmpty()) {
                    out.println("<img src='data:image/jpeg;base64," + base64Image + "' width='200' height='200'/>");
                }
            } else {
                out.println("<h3>Invalid Username or Password</h3>");
                out.println("<a href='login.html'>Try Again</a>");
            }
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
    }
}
