import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class AddStudentServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
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
		
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","mca6");
			
			String query = "INSERT INTO cbse_students VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, studentId);
            ps.setString(2, studentName);
            ps.setInt(3, computer);
            ps.setInt(4, english);
            ps.setInt(5, science);
            ps.setInt(6, math);
            ps.setInt(7, art);
            ps.setInt(8, hindi);

            int result = ps.executeUpdate();
			
			if (result > 0) {
                res.sendRedirect("studentAdded.html");
            } else {
                res.sendRedirect("studentNotAdded.html");
            }

            c.close();
		}
		catch(Exception e){
			
			req.setAttribute("error", "This Student Id Already Exist!!!");
			RequestDispatcher rd = req.getRequestDispatcher("/error");
			rd.forward(req, res);
			
			System.out.println(e);
		}
	}
}