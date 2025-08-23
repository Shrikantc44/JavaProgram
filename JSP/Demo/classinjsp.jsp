<html>
<body>

<%! 
javax.servlet.jsp.JspWriter localOut;

class BaseClass {
    public void start() throws java.io.IOException {
        localOut.println("Starting....<br>");
    }
}

class DerivedClass1 extends BaseClass {
    public void fly() throws java.io.IOException {
        localOut.println("Flying....<br>");
    }
}
%>

<% 
// Assign JSP out to localOut
localOut = out;

out.println("Creating a DerivedClass1 object...<br>");

// Create object of DerivedClass1
DerivedClass1 p1 = new DerivedClass1();

// Call methods
p1.start();
p1.fly();
%>

</body>
</html>
