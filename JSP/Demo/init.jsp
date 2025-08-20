<html>
<body>

<%! 
String s;
public void jspInit()
{
    ServletConfig con = getServletConfig();
    s = con.getInitParameter("name");
}
%>

<%
String s1 = application.getInitParameter("name1");   // context-param
String s2 = config.getInitParameter("name");         // init-param
out.println(s1);
out.println("<br>");
out.println(s2 + s);
%>

</body>
</html>
