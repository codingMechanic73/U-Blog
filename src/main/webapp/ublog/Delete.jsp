<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /*If user tries to click on browser back button then he/ she should not be able to access this page*/
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
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
    <title>Delete Post</title>
</head>
<body>
    Logged In as <%=userName %>
    </br> </br>
    <form action="/ublog/post/util" method="POST">
        <table>
            <tr>
               <label for="postId">Post Id </label>
            </tr>
            <tr>
                <input type="number" min="1" placeholder="Post Id" name="postId" required/>
            </tr>
        </table>
        </br>
        <input type="submit" value="Delete" name="requestFrom"/>
        <a href="/Home.jsp">Home Page </a>
    </form>
    <%
     String result = (String)request.getAttribute("DeleteSuccess");
     if (result == null) {result = "";}
     %>
     <%=result %>
</body>
</html>
