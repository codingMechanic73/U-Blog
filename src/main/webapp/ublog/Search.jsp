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

<htm>
<head>
    <title>Search Post</title>
</head>
<body>
    Logged In as <%=userName %>
    </br>
    </br>
    <form action="/ublog/post/util" method="POST">
        <table>
            <tr>
                <td>
                    <label for="email">User Email:</label>
                </td>
                <td>
                    <input placeholder="example@email.com" required type="email" name="sEmail" />
                </td>
            </tr>
        </table>
        <input type="submit" value="Search" name="requestFrom"/>
        <a href="/Home.jsp">Home Page</a>
    </form>
<%! String error = ""; %>
<%
    error = (String)request.getAttribute("error");
    if (error == null) {error = "";}
%>
<%=error %>

    <%
        List<PostDTO> posts = (List<PostDTO>) request.getAttribute("posts");
        if (posts != null) {
        for (PostDTO post: posts) { %>
            <div>
                 <table>
                    <tr>
                         <td>
                         <label for="postId">Post Id:</label>
                         </td>
                         <td>
                         <%=post.getPostId() %>
                         </td>
                     </tr>
                    <tr>
                        <td>
                        <label for="email">Email Id:</label>
                        </td>
                        <td>
                        <%=post.getEmailId() %>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <label for="blog-title">Title:</label>
                        </td>
                        <td>
                            <%=post.getTitle() %>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <label for="blog-tag">Tag:</label>
                        </td>
                        <td>
                            <%=post.getTag() %>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <label for="blog-desc">Description:</label>
                        </td>
                        <td>
                            <%=post.getDescription() %>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <label for="timestamp">Time:</label>
                        </td>
                        <td>
                            <%=DateTimeFormatter.format(post.getTimestamp()) %>
                        </td>
                    </tr>
                </table>
            </div>
            <hr>
       <% }
       }
    %>
</body>

</html>
