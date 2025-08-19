import javax.servlet.http.*; // HttpServlet, HttpServletRequest, HttpServletResponse ke liye
import javax.servlet.*;       // ServletException, RequestDispatcher ke liye
import java.io.*;             // PrintWriter ke liye
import java.sql.*;            // JDBC (Connection, Statement, ResultSet) ke liye

public class ValidateServlet extends HttpServlet {
    
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        res.setContentType("text/html"); // Response ka type HTML set kiya
        PrintWriter out = res.getWriter(); // Response stream ka writer object banaya
        System.out.println("validate Servlet"); // Console pe message print hoga (debug ke liye)
        
        // Request se username aur password liya
        String name = req.getParameter("name"); 
        String pass = req.getParameter("pass");

        try {
            // Oracle JDBC driver load kiya
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Oracle DB se connection banaya
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");
            
            // Statement banaya query execute karne ke liye
            Statement s = c.createStatement();
            
            // SQL query banayi username aur password ke basis pe
            String s1 = "select * from emp101 where name='" + name + "' and pass='" + pass + "'";
            
            // Query execute ki aur result ResultSet me store kiya
            ResultSet rs = s.executeQuery(s1);
            
            if(rs.next()) {
                // Agar record mil gaya to welcome page pe forward kiya
                RequestDispatcher rd = req.getRequestDispatcher("action?check=wel");
                rd.forward(req, res);
            } else {
                // Agar record nahi mila to error page pe forward kiya
                RequestDispatcher rd1 = req.getRequestDispatcher("action?check=err");
                rd1.forward(req, res);
            }

        } catch (Exception e) {
            // Agar koi error aata hai to usse console pe print karo
            out.println(e);
        }
    }
}
