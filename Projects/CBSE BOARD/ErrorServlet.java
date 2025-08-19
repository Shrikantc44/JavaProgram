import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;

public class ErrorServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Error - CodeSquadz India</title>");
        out.println("<link rel='stylesheet' href='style.css'>");
        out.println("<style>");
        out.println(".error-message {");
        out.println("    text-align: center;");
        out.println("    font-size: 24px;");
        out.println("    font-weight: bold;");
        out.println("    color: #ff4d4d;");
        out.println("    margin-bottom: 20px;");
        out.println("}");
        out.println(".error-box button {");
        out.println("    width: auto;");
        out.println("    padding: 10px 20px;");
        out.println("    font-size: 16px;");
        out.println("    background-color: #dc3545;");
        out.println("    border: none;");
        out.println("    border-radius: 8px;");
        out.println("    cursor: pointer;");
        out.println("    color: white;");
        out.println("    transition: background-color 0.3s;");
        out.println("}");
        out.println(".error-box button:hover {");
        out.println("    background-color: #b02a37;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>CodeSquadz India</h1>");
		
		String errorType = (String)req.getAttribute("error");
		
        out.println("<div class='box error-box'>");
        out.println("    <div class='error-message'>");
        out.println(errorType + " ! Please try again.");
        out.println("    </div>");

        out.println("    <div style='text-align: center;'>");
        out.println("        <button onclick=\"window.location.href='login.html'\">Try Again</button>");
        out.println("    </div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");

        out.close();
	}
}