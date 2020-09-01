package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.DatabaseConnection;
import com.upgrad.ublog.dto.PostDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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
            LocalDateTime dateTime = LocalDateTime.parse(rs.getString("timestamp"), formatter);
            post.setTimestamp(dateTime);
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
            LocalDateTime dateTime = LocalDateTime.parse(rs.getString("timestamp"), formatter);
            post.setTimestamp(dateTime);
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
            LocalDateTime dateTime = LocalDateTime.parse(rs.getString("timestamp"), formatter);
            post.setTimestamp(dateTime);
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
