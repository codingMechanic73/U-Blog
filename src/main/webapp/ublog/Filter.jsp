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

<%!
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
        String email = (String)session.getAttribute("email");
        int endIndex = email.indexOf("@");
        userName = email.substring(0, endIndex);
    } catch (NullPointerException e) {
        response.sendRedirect("/index.jsp");
    }
%>

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