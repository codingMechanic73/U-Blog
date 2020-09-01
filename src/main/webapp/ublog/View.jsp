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

<!--
	TODO: 4.24. Check if user is logged in or not. If not then redirect user to the default page i.e index.jsp.
	(Hint: You need to handle NullPointerException.)
	(Hint: Make use of the email id stored in the session object to check if user is logged in or not.)
-->
<%!
    String email = "";
    String userName = "";
    PostDTO postDTO;
%>

<%
    try {
        postDTO = (PostDTO)request.getAttribute("postDTO");
        System.out.println(postDTO.getEmailId() + "eamil");
        email = (String)session.getAttribute("email");
        int endIndex = email.indexOf("@");
        userName = email.substring(0, endIndex);
        if (postDTO.getEmailId() == null) {
            response.sendRedirect("/index.jsp");
        }
    } catch (NullPointerException e) {
        response.sendRedirect("/index.jsp");
    }
%>

<!--
    TODO: 4.25. If the user is logged in then display the string before @ in the user email id
    on this web page. For example, if email id is example@gmail.com, then display "Logged In as example"
    in the top left corner of the web page as shown on the learn platform.
-->

<!--
    TODO: 4.26. Design the "View Post After Creation" page with the following properties.
        1. The title of the page should be "View Post After Creation".
        2. Retrieve the PostDTO object from the request object and print the data on this page as shown
            on the learn platform. (Hint: You need to use the JSP expression tag to get the value from
            the PostDTO object and display on the browser)
        3. Use the table tag to align the fields.
        4. Provide a link to the "Home Page".
        5. You also need to handle "NullPointerException".
-->
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



<!--
    TODO
    postDTO.getEmail() throws error in html
-->