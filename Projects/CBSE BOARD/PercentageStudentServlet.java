import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class PercentageStudentServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String studentId = req.getParameter("studentId");

        try {
            // Load Oracle driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to database
            Connection c = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            // Prepare query to fetch student's marks
            String query = "SELECT * FROM cbse_students WHERE student_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Calculate total marks (columns 3 to 8 are subject marks)
                int total = 0;
                for (int i = 3; i <= 8; i++) {
                    total += rs.getInt(i);
                }

                // Calculate percentage
                double percentage = total / 6.0;

                // Set attributes to forward to result page
                req.setAttribute("total", total);
                req.setAttribute("percentage", percentage);

                RequestDispatcher rd = req.getRequestDispatcher("/percentageResult");
                rd.forward(req, res);
            } else {
                // Student not found
                req.setAttribute("error", "This Student ID does not exist!");
                RequestDispatcher rd = req.getRequestDispatcher("/error");
                rd.forward(req, res);
            }

            c.close();
        } catch (Exception e) {
            e.printStackTrace();

            req.setAttribute("error", "" + e);
            RequestDispatcher rd = req.getRequestDispatcher("/error");
            rd.forward(req, res);
        }
    }
}
