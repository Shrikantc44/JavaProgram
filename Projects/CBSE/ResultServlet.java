import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // --- Configure these according to your DB ---
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cbseresults?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "shrik";

    // If you prefer JNDI datasource in WebLogic, comment above and use lookup inside doGet (example later).
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roll = request.getParameter("roll");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!doctype html><html><head><meta charset='utf-8'><title>Result</title>");
        out.println("<link rel='stylesheet' href='styles.css'>");
        out.println("</head><body><div class='container'>");
        out.println("<header><h1>CBSE Result Checker</h1></header>");

        if (roll == null || roll.trim().isEmpty()) {
            out.println("<p>Please provide a roll number. <a href='index.html'>Back</a></p>");
            out.println("</div></body></html>");
            return;
        }

        // sanitize
        roll = roll.trim();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC driver (ensure mysql-connector-java.jar in WEB-INF/lib or server lib)
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String sql = "SELECT name, subject1, subject2, subject3, subject4, subject5 FROM students WHERE roll = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, roll);

            rs = ps.executeQuery();

            if (!rs.next()) {
                out.println("<p>No record found for roll number: <strong>" + escapeHtml(roll) + "</strong></p>");
                out.println("<p><a href='index.html'>Back</a></p>");
            } else {
                String name = rs.getString("name");
                int s1 = rs.getInt("subject1");
                int s2 = rs.getInt("subject2");
                int s3 = rs.getInt("subject3");
                int s4 = rs.getInt("subject4");
                int s5 = rs.getInt("subject5");

                int total = s1 + s2 + s3 + s4 + s5;
                int maxTotal = 5 * 100; // 5 subjects each 100
                double percentage = ( (double) total / maxTotal ) * 100.0;

                // format percent to 2 decimal places
                DecimalFormat df = new DecimalFormat("0.00");
                String percentStr = df.format(percentage);

                // Determine pass/fail simple rule: >=33% and each subject >=33 (adjust as needed)
                boolean passed = percentage >= 33 && s1>=33 && s2>=33 && s3>=33 && s4>=33 && s5>=33;

                out.println("<h2>Result for Roll: " + escapeHtml(roll) + "</h2>");
                out.println("<p><strong>Name:</strong> " + escapeHtml(name) + "</p>");

                out.println("<table class='result-table'>");
                out.println("<thead><tr><th>Subject</th><th>Marks (out of 100)</th></tr></thead>");
                out.println("<tbody>");
                out.println("<tr><td>Maths</td><td>" + s1 + "</td></tr>");
                out.println("<tr><td>Science</td><td>" + s2 + "</td></tr>");
                out.println("<tr><td>Social Science</td><td>" + s3 + "</td></tr>");
                out.println("<tr><td>English</td><td>" + s4 + "</td></tr>");
                out.println("<tr><td>Hindi</td><td>" + s5 + "</td></tr>");
                out.println("<tr><th>Total</th><th>" + total + " / " + maxTotal + "</th></tr>");
                out.println("<tr><th>Percentage</th><th>" + percentStr + " %</th></tr>");
                out.println("<tr><th>Status</th><th class='" + (passed ? "passed" : "failed") + "'>" + (passed ? "Passed" : "Failed") + "</th></tr>");
                out.println("</tbody></table>");

                out.println("<p><a href='index.html'>Check another roll</a></p>");
            }

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<p style='color:red;'>Error: " + escapeHtml(e.getMessage()) + "</p>");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) {}
            try { if (ps != null) ps.close(); } catch (Exception ex) {}
            try { if (conn != null) conn.close(); } catch (Exception ex) {}
            out.println("</div></body></html>");
            out.close();
        }
    }

    // minimal HTML escape to avoid simple injection
    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<","&lt;").replace(">", "&gt;").replace("\"","&quot;");
    }
}
