package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.DatabaseConnection;
import com.upgrad.ublog.dto.UserDTO;

import java.sql.*;

/**
 * TODO: 6.5. Implement the UserDAO interface and implement this class using the Singleton pattern.
 * (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 * UserDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 6.6. findByEmail() method should take email id as an input parameter and
 * return the user details corresponding to the email id from the UBLOG_USERS table defined
 * in the database. (Hint: You should get the connection using the DatabaseConnection class)
 * TODO: 6.7. create() method should take user details as input and insert these details
 * into the UBLOG_USERS table. Return the same UserDTO object which was passed as an input
 * argument. (Hint: You should get the connection using the DatabaseConnection class)
 */

public class UserDAOImpl implements UserDAO {

    public static void main(String[] args) {
		try {
			UserDAO userDAO = new UserDAOImpl();
			UserDTO temp = new UserDTO();
			temp.setUserId(1);
			temp.setEmailId("temp@temp.temp");
			temp.setPassword("temp");
			userDAO.create(temp);
			System.out.println(userDAO.findByEmail("temp@temp.temp"));
		} catch (Exception e) {
			System.out.println("FAILED");
		}

		 // Following should be the desired output of the main method.
		 // UserDTO{userId=11, emailId='temp@temp.temp', password='temp'}
	}

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

        //oracle
        String oracleQuery = "INSERT INTO UBLOG_USERS(email_id, password) VALUES (?, ?);";
        //mySql
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
        //oracle
        String oracleQuery = "SELECT * FROM UBLOG_USERS WHERE email_id = '" + emailId + "';";
        //mySql
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
