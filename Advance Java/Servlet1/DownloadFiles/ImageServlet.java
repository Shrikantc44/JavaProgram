// File handling ke liye I/O classes import ki ja rahi hain
import java.io.*;

// Servlet API ke core classes
import javax.servlet.*;

// HTTP-specific classes (HttpServlet, HttpServletRequest, HttpServletResponse etc.)
import javax.servlet.http.*;

// Utility classes (like Enumeration)
import java.util.*;

// Date formatting ke liye class (though not used in this code)
import java.text.DateFormat;

// Servlet class jo HttpServlet ko extend karti hai
public class ImageServlet extends HttpServlet 
{
    // HTTP GET request handle karne ke liye doGet method override kiya gaya hai
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException 
    {
        // File ka naam jise client ko download karwana hai
        String fileName = "baby.jpeg";

        // Output stream jisse data client tak bhejenge
        ServletOutputStream stream = null;

        // BufferedInputStream for efficient file reading
        BufferedInputStream buf = null;

        // Output stream ko response object se initialize kiya gaya hai
        stream = response.getOutputStream();

        // Server ke real path se image file ka path get kiya ja raha hai
        String s1 = getServletContext().getRealPath("/files/baby.jpeg");

        // File object banaya gaya hai jis path se image read hogi
        File doc = new File(s1);

        // Response ka content type set kiya gaya hai (image/jpeg)
        response.setContentType("image/jpeg");

        // Response header set kiya gaya hai jisse browser file ko download kare
        // "attachment" ka matlab file download hogi, "filename" browser ko suggest karta hai file ka naam
        response.addHeader(
            "Content-Disposition", 
            "attachment; filename=" + fileName
        );

        // Response body ki length set ki ja rahi hai (file ke size ke equal)
        response.setContentLength((int)doc.length());

        // FileInputStream se file read ki ja rahi hai
        FileInputStream input = new FileInputStream(doc);

        // FileInputStream ko BufferedInputStream se wrap kiya for better performance
        buf = new BufferedInputStream(input);

        int readBytes = 0;

        // File ke contents ko ek ek byte read karke output stream me likha ja raha hai
        while ((readBytes = buf.read()) != -1)
            stream.write(readBytes);

        // (Note: best practice ke liye stream.close() and buf.close() karna chahiye yahan)
    }
}
