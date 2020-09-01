package com.upgrad.ublog.services;

import com.upgrad.ublog.dao.DAOFactory;
import com.upgrad.ublog.dto.UserDTO;

public class UserServiceImpl implements UserService {

    private static DAOFactory daoFactory;
    private static UserServiceImpl userService;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {

        if (userService == null) {
            daoFactory = new DAOFactory();
            userService = new UserServiceImpl();
        }

        return userService;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) throws Exception {
        daoFactory.getUserDAO().create(userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getUser(String emailId) throws Exception {
        return daoFactory.getUserDAO().findByEmail(emailId);
    }
}
