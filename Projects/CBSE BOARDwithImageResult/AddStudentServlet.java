import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

@MultipartConfig
public class AddStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        
        String studentId = null;
        String studentName = null;
        int computer = 0;
        int english = 0;
        int science = 0;
        int math = 0;
        int art = 0;
        int hindi = 0;
        InputStream photoStream = null;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Read all parts of the multipart request
            for (Part part : req.getParts()) {
                String name = part.getName();
                
                if (name.equals("studentId")) {
                    studentId = readPartAsString(part);
                } else if (name.equals("studentName")) {
                    studentName = readPartAsString(part);
                } else if (name.equals("computer")) {
                    computer = validateMark(readPartAsString(part));
                } else if (name.equals("english")) {
                    english = validateMark(readPartAsString(part));
                } else if (name.equals("science")) {
                    science = validateMark(readPartAsString(part));
                } else if (name.equals("math")) {
                    math = validateMark(readPartAsString(part));
                } else if (name.equals("art")) {
                    art = validateMark(readPartAsString(part));
                } else if (name.equals("hindi")) {
                    hindi = validateMark(readPartAsString(part));
                } else if (name.equals("photo")) {
                    if (part.getSize() > 0) {
                        photoStream = part.getInputStream();
                    }
                }
            }

            // Validate required fields
            if (studentId == null || studentId.trim().isEmpty()) {
                throw new ServletException("Student ID is required");
            }
            if (studentName == null || studentName.trim().isEmpty()) {
                throw new ServletException("Student Name is required");
            }

            // Database operation
            con = DBUtil.getConnection();
            String sql = "INSERT INTO CBSE_STUDENTS VALUES (?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, studentId);
            ps.setString(2, studentName);
            ps.setInt(3, computer);
            ps.setInt(4, english);
            ps.setInt(5, science);
            ps.setInt(6, math);
            ps.setInt(7, art);
            ps.setInt(8, hindi);
            
            if (photoStream != null) {
                ps.setBinaryStream(9, photoStream);
            } else {
                ps.setNull(9, Types.BLOB);
            }

            int rows = ps.executeUpdate();
            if (rows > 0) {
                res.sendRedirect("student-added.html");
            } else {
                throw new ServletException("Failed to add student");
            }

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error").forward(req, res);
        } finally {
            DBUtil.closeQuietly(photoStream);
            DBUtil.closeQuietly(ps);
            DBUtil.closeQuietly(con);
        }
    }

    private String readPartAsString(Part part) throws IOException {
        if (part == null) return null;
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        int length;
        while ((length = reader.read(buffer)) > 0) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }

    private int validateMark(String mark) throws ServletException {
        if (mark == null || mark.trim().isEmpty()) return 0;
        try {
            int score = Integer.parseInt(mark.trim());
            if (score < 0 || score > 100) {
                throw new ServletException("Marks must be between 0-100");
            }
            return score;
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid marks value");
        }
    }
}