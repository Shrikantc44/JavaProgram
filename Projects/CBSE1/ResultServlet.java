import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class ResultServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        String method = req.getMethod();

        if ("POST".equalsIgnoreCase(method)) {
            doPost(req, res);
        } else if ("GET".equalsIgnoreCase(method)) {
            doGet(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String rollStr = req.getParameter("roll");

        out.println("<html><head><title>CBSE Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial; background-color: #f8f9fa; }");
        out.println("h2 { text-align: center; color: #333; }");
        out.println("table { border-collapse: collapse; width: 50%; margin: auto; background: white; }");
        out.println("th, td { border: 1px solid black; padding: 8px; text-align: center; }");
        out.println(".pass { color: green; font-weight: bold; }");
        out.println(".fail { color: red; font-weight: bold; }");
        out.println("</style></head><body>");
        out.println("<h2>CBSE Result Portal</h2>");

        if (rollStr == null || rollStr.trim().isEmpty()) {
            out.println("<p style='color:red; text-align:center;'>Roll number is required.</p>");
            out.println("</body></html>");
            return;
        }

        try {
            int roll = Integer.parseInt(rollStr);

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE roll=?");
            ps.setInt(1, roll);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int s1 = rs.getInt("subject1");
                int s2 = rs.getInt("subject2");
                int s3 = rs.getInt("subject3");
                int s4 = rs.getInt("subject4");
                int s5 = rs.getInt("subject5");

                int total = s1 + s2 + s3 + s4 + s5;
                double percentage = (total / 500.0) * 100;
                boolean pass = s1 >= 33 && s2 >= 33 && s3 >= 33 && s4 >= 33 && s5 >= 33;

                out.println("<p style='text-align:center;'><b>Roll No:</b> " + roll + "</p>");
                out.println("<p style='text-align:center;'><b>Name:</b> " + name + "</p>");
                out.println("<table>");
                out.println("<tr><th>Subject</th><th>Marks</th></tr>");
                out.println("<tr><td>Subject 1</td><td>" + s1 + "</td></tr>");
                out.println("<tr><td>Subject 2</td><td>" + s2 + "</td></tr>");
                out.println("<tr><td>Subject 3</td><td>" + s3 + "</td></tr>");
                out.println("<tr><td>Subject 4</td><td>" + s4 + "</td></tr>");
                out.println("<tr><td>Subject 5</td><td>" + s5 + "</td></tr>");
                out.println("<tr><th>Total</th><th>" + total + "</th></tr>");
                out.println("<tr><th>Percentage</th><th>" + String.format("%.2f", percentage) + "%</th></tr>");
                out.println("<tr><th>Status</th><th class='" + (pass ? "pass" : "fail") + "'>" 
                            + (pass ? "Pass" : "Fail") + "</th></tr>");
                out.println("</table>");
            } else {
                out.println("<p style='color:red; text-align:center;'>No record found for Roll Number: " + rollStr + "</p>");
            }

            conn.close();
        } catch (NumberFormatException e) {
            out.println("<p style='color:red; text-align:center;'>Invalid Roll Number format.</p>");
        } catch (Exception e) {
            out.println("<p style='color:red; text-align:center;'>Error: " + e.getMessage() + "</p>");
        }

        out.println("<div style='text-align:center; margin-top:20px;'><a href='index.html'>Check Another Result</a></div>");
        out.println("</body></html>");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
