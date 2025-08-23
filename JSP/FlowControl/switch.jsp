<%! int day = 2; %>
<html>
<body>
<% 
switch(day)
{
    case 0: %>
        <font color="red" size="10">
            JSP Tutorial
        </font><br/>
    <% break;
    
    case 1: %>
        <font color="red" size="10">
            Its Monday
        </font><br/>
    <% break;
    
    case 2: %>
        <font color="red" size="10">
            Its Tuesday
        </font><br/>
    <% break;
    
    case 3: 
        out.println("It's Wednesday.");
        break;
    case 4: 
        out.println("It's Thursday.");
        break;
    case 5: 
        out.println("It's Friday.");
        break;
    default:
        out.println("It's Saturday.");
}
%>
</body>
</html>
