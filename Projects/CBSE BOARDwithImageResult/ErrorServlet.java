

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ErrorServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doPost(req, res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        String msg = (String) req.getAttribute("error");
        if (msg == null) msg = "Unknown error";
        out.println("<!DOCTYPE html><html><head><title>Error</title><link rel='stylesheet' href='style.css'></head>");
        out.println("<body><div class='container'><h1>Error</h1><p class='error'>" + msg + "</p>");
        out.println("<div class='actions'><a class='btn' href='index.html'>Back</a></div>");
        out.println("</div></body></html>");
        out.close();
    }
}
