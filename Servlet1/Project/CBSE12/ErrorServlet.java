import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ErrorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = request.getParameter("message");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Error</title></head><body>");
        out.println("<div style='font-family:Arial;max-width:600px;margin:40px auto;background:#fff;padding:20px;border-radius:6px;box-shadow:0 2px 10px rgba(0,0,0,0.1)'>");
        out.println("<h2 style='color:#c0392b'>Error</h2>");
        out.println("<p>" + (message != null ? escapeHtml(message) : "An unknown error occurred") + "</p>");
        out.println("<a href='index.html' style='display:inline-block;padding:8px 12px;background:#2980b9;color:#fff;text-decoration:none;border-radius:4px'>Back</a>");
        out.println("</div></body></html>");
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
    }
}
