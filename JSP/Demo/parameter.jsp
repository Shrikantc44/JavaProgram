<html>
<body>
<%
String name=request.getParameter("name");
String pass=request.getParameter("pass");
RequestDispatcher rd = request.getRequestDispatcher("demo.jsp");
rd.include(request,response);
if(name.equals("shrikant") && pass.equals("shrikant"))
	out.println("valid");
else
	out.println("invalid");

%>

</body>
</html>