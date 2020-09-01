package com.upgrad.ublog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {


    private DatabaseConnection() {
    }

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        return getInstance();
    }

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/mDataSource";
                connection = DriverManager.getConnection(url, "root", "H@cks@lt1");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
