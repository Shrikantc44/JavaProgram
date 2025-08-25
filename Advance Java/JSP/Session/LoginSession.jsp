<html>
<body>
<%@ page import="java.sql.*" %>
<%@ page session="false" %>

<%
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");

    try {
        // Load Oracle Driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Connect to Oracle Database
        Connection c = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:xe", "system", "mca6");

        // Create SQL Statement
        Statement s2 = c.createStatement();
        String s1 = "select * from emp101 where name='" + name + "' and pass='" + pass + "'";
        ResultSet rs1 = s2.executeQuery(s1);

        if (rs1.next()) {
            // Session Handling
            HttpSession s = request.getSession();
            s.setAttribute("name", name);
            s.setAttribute("pass", pass);

            out.println("Data set successfully. New session: " + s.isNew());
            out.println("<br>");
            out.println("<a href='session1.jsp'>Welcome</a>");
        } else {
            out.println("User is Invalid");
        }

    } catch (Exception e) {
        out.println(e);
    }
%>

</body>
</html>
