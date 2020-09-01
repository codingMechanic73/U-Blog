<%@ page import="com.upgrad.ublog.dto.PostDTO" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ page import="com.upgrad.ublog.utils.DateTimeFormatter" %>
<%@ page import="com.upgrad.ublog.services.ServiceFactory" %>
<%@ page import="com.upgrad.ublog.services.PostService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /*If user tries to click on browser back button then he/ she should not be able to access this page*/
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>

<!--
	TODO: 7.26. Check if user is logged in or not. If not then redirect user to the default page i.e index.jsp.
	(Hint: You need to handle NullPointerException.)
	(Hint: Make use of the email id stored in the session object to check if user is logged in or not.)
-->
<%!
    String email = "";
    String userName = "";
    PostService postService = null;
    public void jspInit()
    {
        ServiceFactory serviceFactory = new ServiceFactory();
        postService = serviceFactory.getPostService();
    }
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
	TODO: 7.27. Design the "Filter Post" page with the following properties.
	    1. Title of the page should be "Filter Post"
	    2. Provide method and action attributes of the form tag such that when user submit
	        the form, the doPost() method of PostUtilServlet get invoked.
	    3. Provide "Select tag:" label along with the dropdown menu as shown on the learn platform.
	    4. Also, for the dropdown menu, load all the unique tags using the getAllTags() method of the
	        PostService class. (Note: You should use ServiceFactory to get the PostService object)
	    5. Provide a "Filter" submit button.
        6. Provide a link to the "Home Page".
-->

<html>
<head><title>Filter Post</title></head>
<body>
    Logged In as <%=userName %>
    </br> </br>

    <form action="/ublog/post/util" method="POST">
        <label for="selectTag">Select tag</label>
        <select name="selectTag">
            <%
                try {
                    Set<String> set = postService.getAllTags();
                    for (String s: set) {
                        %>
                        <option value=<%=s %>>
                            <%=s %>
                        </option>
                        <%
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </select>
        </br> </br>
        <input type="submit" value="Filter" name="requestFrom"/>
        <a href="/Home.jsp">Home Page</a>

    </form>
    <%
        String result = (String)request.getAttribute("filterSuccess");
        if (result == null) {result = "";}
     %>
     <%= result %>

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
                         <label for="email">Email:</label>
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
                     </form>
                 </table>
             </div>
             <hr>
        <% }
        }
         %>
</body>
</html>
<!--
    TODO: 7.28. If the user is logged in then display the string before @ in the user email id
    on this web page. For example, if email id is example@gmail.com, then display "Logged In as example"
    in the top left corner of the web page as shown on the learn platform.
-->

<!--
    TODO: 7.30. Check if there exists an error message in the request object. If so, then display the message
    below the Filter button.
    TODO: 7.31. Check if posts exist in the request object. If so, then display the posts as shown on the learn
    platform.
     Note: Use the table tag to align the fields.
     Note: Try to provide a horizontal rule or some spacing between the posts as shown on the learn platform.
-->