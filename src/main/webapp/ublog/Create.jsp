<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /*If user tries to click on browser back button then he/ she should not be able to access this page*/
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<%!
    String email = "";
    String userName = "";
%>

<%
    try {
        email = (String)session.getAttribute("email");
        int endIndex = email.indexOf("@");
        userName = email.substring(0, endIndex);
    } catch (NullPointerException e) {
        response.sendRedirect("/index.jsp");
    }
%>

<html>
<head>
    <title>Create Post</title>
</head>
<body>
    Logged In as <%=userName %>
    </br>
    </br>
    <form action="/ublog/post" method="POST">
        <table>
            <tr>
                <td>
                <label for="email">User Email:</label>
                </td>
                <td>
                <%=email %>
                </td>
            </tr>
            <tr>
                <td>
                <label for="blog-title">Blog Title:</label>
                </td>
                <td>
                <input type="text" placeholder="Title" name="blog-title" maxlength="200" required/>
                </td>
            </tr>
            <tr>
                <td>
                <label for="blog-tag">Blog Tag:</label>
                </td>
                <td>
                <input type="text" placeholder="java" name="blog-tag" maxlength="10" required/>
                </td>
            </tr>
            <tr>
                <td>
                <label for="blog-desc">Blog Description:</label>
                </td>
                <td>
                <textarea name="blog-desc" placeholder="Post Description" rows="15" cols="75" maxlength="1000" required></textarea>
                </td>
            </tr>
            </form>
        </table>
        <input type="submit" value="Post" />
        <a href="/Home.jsp">Home Page</a>
    </form>
</body>
</html>



