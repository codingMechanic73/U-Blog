<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /*If user tries to click on browser back button then he/ she should not be able to access this page*/
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<%!
    String userName = "";
%>

<%
    try {
        String email = (String)session.getAttribute("email");
        int endIndex = email.indexOf("@");
        userName = email.substring(0, endIndex);
    } catch (NullPointerException e) {
        response.sendRedirect("/index.jsp");
    }
%>

<html>
<head>
    <title>Home Page</title>
</head>

<body>
    Logged In as <%=userName %>
    </br> </br>
    <a href="/ublog/Create.jsp">Create Post</a> </br></br>
    <a href="/ublog/Search.jsp">Search Post</a> </br></br>
    <a href="/ublog/Delete.jsp">Delete Post</a> </br></br>
    <a href="/ublog/Filter.jsp">Filter Post</a> </br></br>
    <a href="/Logout.jsp">Logout</a>
</body>
</html>