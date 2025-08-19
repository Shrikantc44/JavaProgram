import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class StudentPhotoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String studentId = req.getParameter("id");
        if (studentId == null || studentId.trim().isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        OutputStream os = null;

        res.setContentType("image/jpeg"); // assuming JPEG

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement("SELECT photo FROM cbse_students WHERE student_id=?");
            ps.setString(1, studentId);
            rs = ps.executeQuery();

            if (rs.next()) {
                is = rs.getBinaryStream("photo");
                if (is == null) {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                os = res.getOutputStream();
                byte[] buf = new byte[4096];
                int read;
                while ((read = is.read(buf)) != -1) {
                    os.write(buf, 0, read);
                }
                os.flush();
            } else {
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "No student found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } finally {
            DBUtil.closeQuietly(is);
            DBUtil.closeQuietly(os);
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(ps);
            DBUtil.closeQuietly(con);
        }
    }
}
