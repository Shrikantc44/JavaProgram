import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class UpdateStudentServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ServletOutputStream out = res.getOutputStream();

        // extract the form data here
        String studentId = req.getParameter("studentId");
        String studentName = req.getParameter("studentName");

        int computer = Integer.parseInt(req.getParameter("computer"));
        int english = Integer.parseInt(req.getParameter("english"));
        int science = Integer.parseInt(req.getParameter("science"));
        int math = Integer.parseInt(req.getParameter("math"));
        int art = Integer.parseInt(req.getParameter("art"));
        int hindi = Integer.parseInt(req.getParameter("hindi"));

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

            String query = "UPDATE cbse_students SET student_name = ?, computer = ?, english = ?, science = ?, math = ?, art = ?, hindi = ? WHERE student_id = ?";
            PreparedStatement ps = c.prepareStatement(query);

            ps.setString(1, studentName);
            ps.setInt(2, computer);
            ps.setInt(3, english);
            ps.setInt(4, science);
            ps.setInt(5, math);
            ps.setInt(6, art);
            ps.setInt(7, hindi);
            ps.setString(8, studentId); // WHERE clause

            int result = ps.executeUpdate();

            if (result > 0) {
                res.sendRedirect("studentUpdated.html");
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
