import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class AllStudentsServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>All Students - CodeSquadz India</title>");
        out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css\">");
        out.println("<link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap\" rel=\"stylesheet\">");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("<style>");
        out.println(".student-photo { width: 50px; height: 50px; border-radius: 50%; object-fit: cover; }");
        out.println(".action-btn { padding: 5px 10px; margin: 0 3px; font-size: 14px; }");
        out.println(".action-btn i { margin-right: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<header class=\"header\">");
        out.println("<div class=\"container header-content\">");
        out.println("<div class=\"logo\">");
        out.println("<i class=\"fas fa-users\"></i>");
        out.println("<span>All Students</span>");
        out.println("</div>");
        out.println("<nav class=\"nav\">");
        out.println("<a href=\"index.html\"><i class=\"fas fa-arrow-left\"></i> Back</a>");
        out.println("</nav>");
        out.println("</div>");
        out.println("</header>");

        out.println("<main class=\"main\">");
        out.println("<div class=\"container\">");
        out.println("<div class=\"card\">");
        out.println("<div class=\"card-header\">");
        out.println("<h2><i class=\"fas fa-list\"></i> Student Records</h2>");
        out.println("</div>");
        out.println("<div class=\"card-body\">");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            String query = "SELECT * FROM cbse_students ORDER BY student_id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            out.println("<div style=\"overflow-x:auto;\">");
            out.println("<table class=\"table\">");
            out.println("<thead>");
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
            out.println("<th>Actions</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td><img src=\"photo?id=" + rs.getString("student_id") + "\" class=\"student-photo\" onerror=\"this.src='https://via.placeholder.com/50'\"></td>");
                out.println("<td>" + rs.getString("student_id") + "</td>");
                out.println("<td>" + rs.getString("student_name") + "</td>");
                out.println("<td>" + rs.getInt("computer") + "</td>");
                out.println("<td>" + rs.getInt("english") + "</td>");
                out.println("<td>" + rs.getInt("science") + "</td>");
                out.println("<td>" + rs.getInt("math") + "</td>");
                out.println("<td>" + rs.getInt("art") + "</td>");
                out.println("<td>" + rs.getInt("hindi") + "</td>");
                out.println("<td>");
                out.println("<a href=\"updateStudent.html?id=" + rs.getString("student_id") + "\" class=\"btn action-btn\"><i class=\"fas fa-edit\"></i> Edit</a>");
                out.println("<a href=\"deleteStudent.html?id=" + rs.getString("student_id") + "\" class=\"btn btn-danger action-btn\"><i class=\"fas fa-trash\"></i> Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            conn.close();
        } catch (Exception e) {
            out.println("<div class=\"alert alert-danger\">");
            out.println("<i class=\"fas fa-exclamation-triangle\"></i> Error loading student data: " + e.getMessage());
            out.println("</div>");
        }

        out.println("</div>"); // card-body
        out.println("</div>"); // card
        out.println("</div>"); // container
        out.println("</main>");

        out.println("<footer class=\"footer\">");
        out.println("<div class=\"container\">");
        out.println("<p>&copy; 2023 CodeSquadz India. All rights reserved.</p>");
        out.println("</div>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}