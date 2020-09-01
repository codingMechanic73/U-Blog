<%@ page import="com.upgrad.ublog.dto.PostDTO" %>
<%@ page import="com.upgrad.ublog.utils.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
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
    PostDTO postDTO;
%>

<%
    try {
        postDTO = (PostDTO)request.getAttribute("postDTO");
        String email = (String)session.getAttribute("email");
        int endIndex = email.indexOf("@");
        userName = email.substring(0, endIndex);
        if (postDTO.getEmailId() == null) {
            response.sendRedirect("/index.jsp");
        }
    } catch (NullPointerException e) {
        response.sendRedirect("/index.jsp");
    }
%>

<html>
<head>
    <title>View Post After Creation</title>
</head>
<body>
    Logged In as <%=userName %>
    </br>
    </br>
    <table>
        <tr>
            <td>
            <label for="email">Email Id:</label>
            </td>
            <td>
            <span name="email"><%=postDTO.getEmailId()%> </span>
            </td>
        </tr>
        <tr>
            <td>
            <label for="blog-title">Title:</label>
            </td>
            <td>
            <span name="blog-title"><%=postDTO.getTitle()%> </span>
            </td>
        </tr>
        <tr>
            <td>
            <label for="blog-tag">Tag:</label>
            </td>
            <td>
            <span name="blog-tag"><%=postDTO.getTag()%> </span>
            </td>
        </tr>
        <tr>
            <td>
            <label for="blog-desc">Description:</label>
            </td>
            <td>
            <span name="blog-desc"><%=postDTO.getDescription()%> </span>
            </td>
        </tr>
         <tr>
            <td>
            <label for="blog-time">Time:</label>
            </td>
            <td>
            <span name="blog-time">
                <%=DateTimeFormatter.format(postDTO.getTimestamp()) %>
            </td>
        </tr>
        </form>
    </table>
    <a href="/Home.jsp">Home Page</a>
</body>
</html>