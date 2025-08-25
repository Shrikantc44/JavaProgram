import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ResultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cbseresults";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "shrik";

     // HTTP POST method handle karne ke liye doPost method override kiya gaya hai
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
		// Request se "roll_no" parameter ko le rahe hain
        String rollParam = request.getParameter("roll_no");
		
		// Response ka content type HTML set kiya gaya hai
        response.setContentType("text/html; charset=UTF-8");
		
		 // Response writer banaya gaya hai HTML content likhne ke liye
        PrintWriter out = response.getWriter();

        if (rollParam == null || rollParam.trim().isEmpty()) {
            out.println("<p style='color:red; font-size:18px; text-align:center; margin-top:50px;'>Please enter Roll Number.</p>");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT name, maths, science, english, social_science, hindi FROM student_results WHERE roll_no = ?";
            pstmt = conn.prepareStatement(sql);

            int rollNo = Integer.parseInt(rollParam.trim());
            pstmt.setInt(1, rollNo);

            rs = pstmt.executeQuery();

            out.println("<!DOCTYPE html><html><head><title>CBSE Result</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background: #f1f5f9; padding: 20px; }");
            out.println(".container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); }");
            out.println("h1 { text-align: center; color: #007bff; margin-bottom: 20px; }");
            out.println("p { font-size: 16px; color: #333; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("th { background: #007bff; color: white; padding: 10px; }");
            out.println("td { padding: 10px; border: 1px solid #ddd; text-align: center; }");
            out.println("tr:nth-child(even) { background-color: #f8f9fa; }");
            out.println(".percentage { font-size: 18px; font-weight: bold; color: #28a745; margin-top: 20px; }");
            out.println(".btn { display: inline-block; padding: 10px 18px; background: #007bff; color: white; text-decoration: none; border-radius: 6px; margin-top: 20px; }");
            out.println(".btn:hover { background: #0056b3; }");
            out.println("</style></head><body>");
            out.println("<div class='container'>");
            out.println("<h1>Senior School Certificate Examination Results 2025</h1>");

            if (rs.next()) {
                String name = rs.getString("name");
                int maths = rs.getInt("maths");
                int science = rs.getInt("science");
                int english = rs.getInt("english");
                int socialScience = rs.getInt("social_science");
                int hindi = rs.getInt("hindi");

                double percentage = (maths + science + english + socialScience + hindi) / 5.0;

                out.println("<p><strong>Roll No:</strong> " + escapeHtml(String.valueOf(rollNo)) + "</p>");
                out.println("<p><strong>Name:</strong> " + escapeHtml(name) + "</p>");

                out.println("<table>");
                out.println("<tr><th>Subject</th><th>Marks</th></tr>");
                out.println("<tr><td>Mathematics</td><td>" + maths + "</td></tr>");
                out.println("<tr><td>Science</td><td>" + science + "</td></tr>");
                out.println("<tr><td>English</td><td>" + english + "</td></tr>");
                out.println("<tr><td>Social Science</td><td>" + socialScience + "</td></tr>");
                out.println("<tr><td>Hindi</td><td>" + hindi + "</td></tr>");
                out.println("</table>");

                out.println("<p class='percentage'>Percentage: " + String.format("%.2f", percentage) + " %</p>");
            } else {
                out.println("<p style='color:red; font-size:18px; text-align:center;'>No result found for Roll Number: " + escapeHtml(rollParam) + "</p>");
            }

            out.println("<div style='text-align:center;'><a href='index.html' class='btn'>Back</a></div>");
            out.println("</div></body></html>");

        } catch (NumberFormatException nfe) {
            out.println("<p style='color:red; text-align:center;'>Invalid Roll Number format. Use digits only (e.g. 1001).</p>");
        } catch (ClassNotFoundException cnf) {
            out.println("<p style='color:red; text-align:center;'>JDBC Driver not found. Add mysql-connector jar to WEB-INF/lib</p>");
        } catch (SQLException sqle) {
            out.println("<p style='color:red; text-align:center;'>SQL Error: " + escapeHtml(sqle.getMessage()) + "</p>");
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (pstmt != null) pstmt.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;")
                .replace("\"","&quot;").replace("'","&#x27;");
    }
}
