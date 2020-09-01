<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /*If user tries to click on browser back button then he/ she should not be able to access this page*/
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<%
    String email = (String)session.getAttribute("email");
    if (email != null) {
        request.getRequestDispatcher("/Home.jsp").forward(request, response);
    }
%>

<html>
    <head>
        <title>Sign In / Sign Up</title>
    </head>
    <body>
        <form action="/ublog/user" method="POST">
            <table>
                <tr>
                    <td>
                        <label for="email">User Email:</label>
                    </td>
                    <td>
                        <input type="text" name="email" placeholder="example@email.com" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">Password:</label>
                    </td>
                    <td>
                        <input type="password" name="password" placeholder="********" />
                    </td>
                </tr>
            </table>
            <input type="submit" value="Sign In" name="buttonType"/>
            <input type="submit" value="Sign Up" name="buttonType"/>
        </form>
<%! String error = ""; %>
<%
    error = (String)request.getAttribute("error");
    if (error == null) {error = "";}
%>

<%=error %>
</body>
</html>
