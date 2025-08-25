import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class AllStudentsServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>All Students - CodeSquadz India</title>");
        out.println("<link rel='stylesheet' href='style.css'>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; }");
        out.println("table { border-collapse: collapse; width: 90%; margin: auto; font-size: 16px; background-color: rgba(255,255,255,0.9); }");
        out.println("th { background-color: #0d6efd; color: white; padding: 10px; text-align: center; }");
        out.println("td { border: 1px solid #ccc; padding: 10px; text-align: center; color: black; }"); // only <td> black
        out.println("h1, h2 { text-align: center; color: black; }");
        out.println("button { padding: 10px 20px; font-size: 16px; border-radius: 8px; background-color: #198754; color: white; border: none; cursor: pointer; }");
        out.println("button:hover { background-color: #145c33; }");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<h1>CodeSquadz India</h1>");
        out.println("<h2> All Student Records</h2>");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            String query = "SELECT * FROM cbse_students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            out.println("<table>");
            out.println("<tr>");
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
                out.println("<tr>");
                out.println("<td>" + rs.getString("student_id") + "</td>");
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
            conn.close();
        } catch (Exception e) {
            out.println("<p style='color:red; text-align:center;'>Error loading student data.</p>");
            e.printStackTrace(out);
        }

        out.println("<div style='text-align:center; margin-top: 30px;'>");
        out.println("<button onclick=\"window.location.href='login.html'\">Back to Home</button>");
        out.println("</div>");

        out.println("</body></html>");
        out.close();
    }
}
