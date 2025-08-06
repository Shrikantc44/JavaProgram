import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cbse_results";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "yourpassword";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String rollNumber = request.getParameter("rollNumber");
        
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            
            // Create statement
            String sql = "SELECT * FROM students WHERE roll_number = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rollNumber);
            
            // Execute query
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Get student details
                String studentName = rs.getString("student_name");
                String fatherName = rs.getString("father_name");
                String motherName = rs.getString("mother_name");
                String schoolCode = rs.getString("school_code");
                
                // Get marks
                int hindi = rs.getInt("hindi");
                int english = rs.getInt("english");
                int mathematics = rs.getInt("mathematics");
                int science = rs.getInt("science");
                int socialScience = rs.getInt("social_science");
                
                // Calculate total and percentage
                int totalMarks = hindi + english + mathematics + science + socialScience;
                double percentage = (totalMarks / 500.0) * 100;
                
                // Generate HTML response
                out.println("<html>");
                out.println("<head>");
                out.println("<title>CBSE Board Result</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; max-width: 800px; margin: 0 auto; padding: 20px; background-color: #f5f5f5; }");
                out.println(".container { background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
                out.println("h1 { color: #1a5276; text-align: center; }");
                out.println(".student-info { margin-bottom: 20px; }");
                out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
                out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
                out.println("th { background-color: #f2f2f2; }");
                out.println(".total { font-weight: bold; }");
                out.println(".percentage { font-weight: bold; color: #1a5276; font-size: 18px; }");
                out.println(".btn { display: inline-block; margin-top: 20px; background-color: #2874a6; color: white; padding: 10px 15px; text-decoration: none; border-radius: 4px; }");
                out.println(".btn:hover { background-color: #1a5276; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h1>CBSE Board Result 2023</h1>");
                
                // Student information
                out.println("<div class='student-info'>");
                out.println("<p><strong>Roll Number:</strong> " + rollNumber + "</p>");
                out.println("<p><strong>Student Name:</strong> " + studentName + "</p>");
                out.println("<p><strong>Father's Name:</strong> " + fatherName + "</p>");
                out.println("<p><strong>Mother's Name:</strong> " + motherName + "</p>");
                out.println("<p><strong>School Code:</strong> " + schoolCode + "</p>");
                out.println("</div>");
                
                // Marks table
                out.println("<table>");
                out.println("<tr><th>Subject</th><th>Marks</th></tr>");
                out.println("<tr><td>Hindi</td><td>" + hindi + "</td></tr>");
                out.println("<tr><td>English</td><td>" + english + "</td></tr>");
                out.println("<tr><td>Mathematics</td><td>" + mathematics + "</td></tr>");
                out.println("<tr><td>Science</td><td>" + science + "</td></tr>");
                out.println("<tr><td>Social Science</td><td>" + socialScience + "</td></tr>");
                out.println("<tr class='total'><td>Total Marks</td><td>" + totalMarks + " / 500</td></tr>");
                out.println("<tr class='percentage'><td>Percentage</td><td>" + String.format("%.2f", percentage) + "%</td></tr>");
                out.println("</table>");
                
                // Back button
                out.println("<a href='index.html' class='btn'>Check Another Result</a>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<html><body><h2>No record found for roll number: " + rollNumber + "</h2>");
                out.println("<a href='index.html'>Go Back</a></body></html>");
            }
            
            // Close resources
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (ClassNotFoundException e) {
            out.println("<html><body><h2>Error: Database driver not found</h2></body></html>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("<html><body><h2>Error: Database connection problem</h2></body></html>");
            e.printStackTrace();
        }
    }
}