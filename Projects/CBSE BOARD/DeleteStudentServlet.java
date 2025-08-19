import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class DeleteStudentServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ServletOutputStream out = res.getOutputStream();

        // extract the form data here
        String studentId = req.getParameter("studentId");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            String query = "DELETE FROM cbse_students WHERE student_id = ?";
            PreparedStatement ps = c.prepareStatement(query);

            ps.setString(1, studentId); // WHERE clause

            int result = ps.executeUpdate();

            if (result > 0) {
                res.sendRedirect("studentDeleted.html");
            } else {
                req.setAttribute("error", "This Stuedent Id Does Not Exist!!!!!");
				RequestDispatcher rd = req.getRequestDispatcher("/error");
				rd.forward(req, res);
            }

            c.close();
        } catch (Exception e) {
            req.setAttribute("error", "Somthing went wrong!!!!" + e);
            RequestDispatcher rd = req.getRequestDispatcher("/error");
            rd.forward(req, res);
            System.out.println(e);
        }
    }
}
