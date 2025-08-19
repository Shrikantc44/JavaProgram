import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class AllStudentsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html><html><head><title>All Students - CodeSquadz India</title>");
        out.println("<link rel='stylesheet' href='style.css'>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("table { border-collapse: collapse; width: 90%; margin: auto; font-size: 16px; background-color: rgba(255,255,255,0.9); }");
        out.println("th { background-color: #0d6efd; color: white; padding: 10px; text-align: center; }");
        out.println("td { border: 1px solid #ccc; padding: 10px; text-align: center; color: black; }");
        out.println("h1, h2 { text-align: center; color: black; }");
        out.println("button { padding: 10px 20px; font-size: 16px; border-radius: 8px; background-color: #198754; color: white; border: none; cursor: pointer; }");
        out.println("button:hover { background-color: #145c33; }");
        out.println(".thumb { width: 50px; height: 50px; object-fit: cover; border-radius: 50%; }");
        out.println("</style></head><body>");

        out.println("<h1>CodeSquadz India</h1>");
        out.println("<h2> All Student Records</h2>");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String query = "SELECT student_id, student_name, computer, english, science, math, art, hindi FROM cbse_students ORDER BY student_id";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Photo</th>");
            out.println("<th>Student ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Computer</th>");
            out.println("<th>English</th>");
            out.println("<th>Science</th>");
            out.println("<th>Math</th>");
            out.println("<th>Art</th>");
            out.println("<th>Hindi</th>");
            out.println("</tr>");

            while (rs.next()) {
                String id = rs.getString("student_id");
                out.println("<tr>");
                out.println("<td><img class='thumb' src='photo?id=" + id + "' onerror=\"this.src='assets/no-photo.png'\"/></td>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString("student_name") + "</td>");
                out.println("<td>" + rs.getInt("computer") + "</td>");
                out.println("<td>" + rs.getInt("english") + "</td>");
                out.println("<td>" + rs.getInt("science") + "</td>");
                out.println("<td>" + rs.getInt("math") + "</td>");
                out.println("<td>" + rs.getInt("art") + "</td>");
                out.println("<td>" + rs.getInt("hindi") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        } catch (Exception e) {
            out.println("<p style='color:red; text-align:center;'>Error loading student data: " + e.getMessage() + "</p>");
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(conn);
        }

        out.println("<div style='text-align:center; margin-top: 30px;'>");
        out.println("<button onclick=\"window.location.href='index.html'\">Back to Home</button>");
        out.println("</div>");

        out.println("</body></html>");
        out.close();
    }
}
