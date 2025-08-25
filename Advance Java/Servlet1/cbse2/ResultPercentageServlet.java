import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ResultPercentageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

		double percentage = ((Double) req.getAttribute("percentage")).doubleValue();
		String formattedPercentage = String.format("%.2f", percentage);

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Percentage - CodeSquadz India</title>");
        out.println("<link rel='stylesheet' href='style.css'>");
        out.println("<style>");
        out.println(".percentage-message { text-align: center; font-size: 26px; font-weight: bold; color: #00ffcc; margin-bottom: 20px; }");
        out.println(".percentage-box { text-align: center; }");
        out.println(".percentage-value { font-size: 48px; font-weight: bold; color: #ffffff; background-color: rgba(13, 110, 253, 0.8); padding: 20px 40px; border-radius: 15px; display: inline-block; margin-top: 10px; }");
        out.println(".back-button { margin-top: 30px; padding: 10px 20px; font-size: 16px; border: none; border-radius: 8px; background-color: #0d6efd; color: white; cursor: pointer; }");
        out.println(".back-button:hover { background-color: #0b5ed7; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>CodeSquadz India</h1>");

        out.println("<div class='box percentage-box'>");
        out.println("  <div class='percentage-message'> Student's Percentage Marks</div>");
        out.println("  <div class='percentage-value'>" + formattedPercentage + "%</div>");
        out.println("  <div><button class='back-button' onclick=\"window.location.href='login.html'\">Back to Home</button></div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
