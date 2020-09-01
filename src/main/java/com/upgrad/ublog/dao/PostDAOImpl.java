package com.upgrad.ublog.dao;

/**
 * TODO: 6.19. Implement the PostsDAO interface and implement this class using the Singleton pattern.
 * (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 * PostDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * Note: getPostDAO() method of the DAOFactory should return the PostDAOImpl object using
 * getInstance() method of the PostDAOImpl class
 * TODO: 6.20. Define the following methods and return null for each of them. You will provide a
 * proper implementation for each of these methods, later in this project.
 * a. findByEmail()
 * b. findByTag()
 * c. findAllTags()
 * d. findById()
 * e. deleteById() (return false for this method for now)
 * TODO: 6.21. create() method should take post details as input and insert these details into
 * the UBLOG_POSTS table. Return the same PostDTO object which was passed as an input argument.
 * (Hint: You should get the connection using the DatabaseConnection class)
 * <p>
 * TODO: 7.1. Implement findByEmail() method which takes email id as an input parameter and
 * returns all the posts corresponding to the email id from the UBLOG_POSTS table defined
 * in the database.
 * <p>
 * TODO: 7.13. Implement the deleteById() method which takes post id as an input argument and delete
 * the corresponding post from the database. If the post was deleted successfully, then return true,
 * otherwise, return false.
 * TODO: 7.14. Implement the findById() method which takes post id as an input argument and return the
 * post details from the database. If no post exists for the given id, then return an PostDTO object
 * without setting any of its attributes.
 */

/**
 * TODO: 7.1. Implement findByEmail() method which takes email id as an input parameter and
 *  returns all the posts corresponding to the email id from the UBLOG_POSTS table defined
 *  in the database.
 */

/**
 * TODO: 7.13. Implement the deleteById() method which takes post id as an input argument and delete
 *  the corresponding post from the database. If the post was deleted successfully, then return true,
 *  otherwise, return false.
 * TODO: 7.14. Implement the findById() method which takes post id as an input argument and return the
 *  post details from the database. If no post exists for the given id, then return an PostDTO object
 *  without setting any of its attributes.
 */

import com.upgrad.ublog.db.DatabaseConnection;
import com.upgrad.ublog.dto.PostDTO;
import com.upgrad.ublog.dto.UserDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 7.22. Implement findAllTags() method which returns a list of all unique tags in the UBLOG_POSTS
 *  table.
 * TODO: 7.23. Implement findByTag() method which takes "tag" as an input argument and returns all the
 *  posts corresponding to the input "tag" from the UBLOG_POSTS table defined in the database.
 */

public class PostDAOImpl implements PostDAO {

    private PostDAOImpl() {
    }

    private static PostDAOImpl postDAO;

    public static PostDAO getInstance() {
        if (postDAO == null) {
            postDAO = new PostDAOImpl();
        }
        return postDAO;
    }


    @Override
    public PostDTO create(PostDTO postDTO) throws SQLException {

        //mySql
        String query = "INSERT INTO UBLOG_POSTS(email_id, title, description, tag, timestamp) VALUES (?, ?, ?, ?, ?);";

        Connection con = DatabaseConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, postDTO.getEmailId());
        st.setString(2, postDTO.getTitle());
        st.setString(3, postDTO.getDescription());
        st.setString(4, postDTO.getTag());
        st.setString(5, com.upgrad.ublog.utils.DateTimeFormatter.format(postDTO.getTimestamp()));
        st.executeUpdate();

        return postDTO;
    }

    @Override
    public List<PostDTO> findByEmail(String emailId) throws SQLException {

        String query = "SELECT * FROM UBLOG_POSTS WHERE email_id = '" + emailId + "';";

        Connection con = DatabaseConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        List<PostDTO> posts = new ArrayList<>();

        while (rs.next()) {
            PostDTO post = new PostDTO();
            post.setEmailId(rs.getString("email_id"));
            post.setPostId(Integer.parseInt(rs.getString("id")));
            post.setTitle(rs.getString("title"));
            post.setDescription(rs.getString("description"));
            post.setTag(rs.getString("tag"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            System.out.println(rs.getString("timestamp"));
            LocalDateTime dateTime = LocalDateTime.parse(rs.getString("timestamp"), formatter);
            post.setTimestamp(dateTime);
            post.setStringTimestamp(rs.getString("timestamp"));
            posts.add(post);
        }

        return posts;
    }

    @Override
    public List<PostDTO> findByTag(String tag) throws SQLException {
        String query = "SELECT * from UBLOG_POSTS where tag ='" + tag + "';";
        Connection con = DatabaseConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        List<PostDTO> posts = new ArrayList<>();
        while (rs.next()) {
            PostDTO post = new PostDTO();
            post.setEmailId(rs.getString("email_id"));
            post.setPostId(Integer.parseInt(rs.getString("id")));
            post.setTitle(rs.getString("title"));
            post.setDescription(rs.getString("description"));
            post.setTag(rs.getString("tag"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            System.out.println(rs.getString("timestamp"));
            LocalDateTime dateTime = LocalDateTime.parse(rs.getString("timestamp"), formatter);
            post.setTimestamp(dateTime);
            post.setStringTimestamp(rs.getString("timestamp"));
            posts.add(post);
        }
        return posts;
    }

    @Override
    public PostDTO findById(int id) throws SQLException {
        String query = "SELECT * FROM UBLOG_POSTS WHERE id = '" + id + "';";
        Connection con = DatabaseConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        PostDTO post = null;
        while (rs.next()) {
            post = new PostDTO();
            post.setEmailId(rs.getString("email_id"));
            post.setPostId(Integer.parseInt(rs.getString("id")));
            post.setTitle(rs.getString("title"));
            post.setDescription(rs.getString("description"));
            post.setTag(rs.getString("tag"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            System.out.println(rs.getString("timestamp"));
            LocalDateTime dateTime = LocalDateTime.parse(rs.getString("timestamp"), formatter);

            post.setStringTimestamp(rs.getString("timestamp"));
        }
        return post;
    }

    @Override
    public List<String> findAllTags() throws SQLException {
        String query = "SELECT tag from UBLOG_POSTS;";
        Connection con = DatabaseConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<String> tags = new ArrayList<>();
        while (rs.next()) {
            tags.add(rs.getString("tag"));
        }
        return tags;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        String query = "DELETE FROM UBLOG_POSTS WHERE id = '" + id + "';";
        Connection con = DatabaseConnection.getConnection();
        Statement st = con.createStatement();
        return st.executeUpdate(query) != 0;
    }
}
