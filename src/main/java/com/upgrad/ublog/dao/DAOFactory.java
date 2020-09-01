package com.upgrad.ublog.dao;

public class DAOFactory {

    public PostDAO getPostDAO() {
        return PostDAOImpl.getInstance();
    }

    public UserDAO getUserDAO() {
        return UserDAOImpl.getInstance();
    }
}
