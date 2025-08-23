<html>
<body>
<%@ page import="java.sql.*" %>
<%@ page session="false" %>

<%
    HttpSession s = request.getSession(false);   // false = नया session create नहीं करेगा

    if (s != null) {
        String name = (String) s.getAttribute("name");
        String pass = (String) s.getAttribute("pass");

        out.println("Welcome " + name);
        out.println("<br>");
        out.println("Session isNew: " + s.isNew());
    } else {
        out.println("Bavari pooch login to kar le!");  // अगर session null है
    }
%>

</body>
</html>
