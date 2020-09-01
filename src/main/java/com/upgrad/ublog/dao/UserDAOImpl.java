package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.DatabaseConnection;
import com.upgrad.ublog.dto.UserDTO;

import java.sql.*;


public class UserDAOImpl implements UserDAO {


    private static UserDAOImpl userDAO;

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    @Override
    public UserDTO create(UserDTO userDTO) throws SQLException {

        String mySQLQuery = "INSERT INTO UBLOG_USERS(email_id, password) VALUES (?, ?);";

        Connection con = DatabaseConnection.getConnection();
        PreparedStatement st = con.prepareStatement(mySQLQuery);
        st.setString(1, userDTO.getEmailId());
        st.setString(2, userDTO.getPassword());
        st.execute();

        return userDTO;
    }

    @Override
    public UserDTO findByEmail(String emailId) throws SQLException {
        String mySQLQuery = "SELECT * FROM UBLOG_USERS WHERE email_id = '" + emailId + "';";

        Connection con = DatabaseConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(mySQLQuery);
        UserDTO user = new UserDTO();
        while (rs.next()) {
            user.setPassword(rs.getString(3));
            user.setEmailId(rs.getString(2));
            user.setUserId(rs.getInt(1));
        }

        return user;
    }
}
