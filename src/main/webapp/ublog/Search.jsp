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
	TODO: 7.3. Check if user is logged in or not. If not then redirect user to the default page i.e index.jsp.
	(Hint: You need to handle NullPointerException.)
	(Hint: Make use of the email id stored in the session object to check if user is logged in or not.)
-->
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
<!--
	TODO: 7.4. Design the "Search Post" page with the following properties.
	    1. Title of the page should be "Search Post"
	    2. Provide method and action attributes of the form tag such that when user submit
	        the form, the doPost() method of PostUtilServlet get invoked.
	    3. Provide "User Email:" label along with the text field as shown on the learn platform.
	    4. Also, for the "User Email:" text field, provide type as email, placeholder as
	        "example@email.com" and make this field required.
	    5. Provide a "Search" submit button.
        6. Provide a link to the "Home Page".
-->
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
                    <input placeholder="example@email.com" required type="email" name="email" />
                </td>
            </tr>
        </table>
        <input type="submit" value="Search" name="requestFrom"/>
        <a href="/Home.jsp">Home Page</a>
    </form>


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

<%! String error = ""; %>
<%
    error = (String)request.getAttribute("error");
    if (error == null) {error = "";}
%>

<%=error %>

<!--
    TODO: 7.5. If the user is logged in then display the string before @ in the user email id
    on this web page. For example, if email id is example@gmail.com, then display "Logged In as example"
    in the top left corner of the web page as shown on the learn platform.
-->

<!--
    TODO: 7.11. Check if there exists an error message in the request object. If so, then display the message
    below the Search button.
    TODO: 7.12. Check if posts exist in the request object. If so, then display the posts as shown on the learn
    platform.
     Note: Use the table tag to align the fields.
     Note: Try to provide a horizontal rule or some spacing between the posts as shown on the learn platform.
-->
