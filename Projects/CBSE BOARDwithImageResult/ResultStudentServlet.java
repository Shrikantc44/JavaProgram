import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class ResultStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html;charset=UTF-8");
        String studentId = req.getParameter("studentId");

        PrintWriter out = res.getWriter();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        out.println("<!DOCTYPE html><html><head><title>Student Result</title>");
        out.println("<link rel='stylesheet' href='style.css'>");
        out.println("</head><body><div class='container'>");
        out.println("<h1>CodeSquadz India</h1>");
        out.println("<h2>Student Result</h2>");

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement("SELECT student_name, computer, english, science, math, art, hindi FROM cbse_students WHERE student_id=?");
            ps.setString(1, studentId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("student_name");
                int computer = rs.getInt("computer");
                int english  = rs.getInt("english");
                int science  = rs.getInt("science");
                int math     = rs.getInt("math");
                int art      = rs.getInt("art");
                int hindi    = rs.getInt("hindi");

                int total = computer + english + science + math + art + hindi;
                double percentage = total / 6.0;

                String grade;
                if (percentage >= 90) grade = "A+";
                else if (percentage >= 80) grade = "A";
                else if (percentage >= 70) grade = "B+";
                else if (percentage >= 60) grade = "B";
                else if (percentage >= 50) grade = "C";
                else grade = "D";

                out.println("<div class='profile'>");
                out.println("<img class='photo' src='photo?id=" + studentId + "' alt='student photo' onerror=\"this.src='assets/no-photo.png'\"/>");
                out.println("<div class='info'>");
                out.println("<p><b>ID:</b> " + studentId + "</p>");
                out.println("<p><b>Name:</b> " + name + "</p>");
                out.println("</div></div>");

                out.println("<table class='marks'>");
                out.println("<tr><th>Subject</th><th>Marks</th></tr>");
                out.println("<tr><td>Computer</td><td>" + computer + "</td></tr>");
                out.println("<tr><td>English</td><td>" + english + "</td></tr>");
                out.println("<tr><td>Science</td><td>" + science + "</td></tr>");
                out.println("<tr><td>Math</td><td>" + math + "</td></tr>");
                out.println("<tr><td>Art</td><td>" + art + "</td></tr>");
                out.println("<tr><td>Hindi</td><td>" + hindi + "</td></tr>");
                out.println("</table>");

                out.println("<div class='summary'>");
                out.println("<p><b>Total:</b> " + total + " / 600</p>");
                out.println("<p><b>Percentage:</b> " + String.format("%.2f", percentage) + "%</p>");
                out.println("<p><b>Grade:</b> " + grade + "</p>");
                out.println("</div>");

                out.println("<div class='actions'>");
                out.println("<a class='btn' href='index.html'>Back</a>");
                out.println("</div>");
            } else {
                out.println("<p class='error'>This Student ID does not exist!</p>");
                out.println("<div class='actions'><a class='btn' href='result-form.html'>Try Again</a></div>");
            }
        } catch (Exception e) {
            out.println("<p class='error'>Something went wrong: " + e.getMessage() + "</p>");
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(ps);
            DBUtil.closeQuietly(con);
        }

        out.println("</div></body></html>");
        out.close();
    }
}
