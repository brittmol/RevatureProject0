package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.User;
import com.revature.p0.exceptions.*;

import java.sql.SQLException;

public class UserService {
    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

    public User registerNewUser(User user) throws SQLException {
        //TODO: check if username already exists and other constraints
        return userDAO.saveUser(user);
    }

    public User updateUser(User user) throws SQLException {
        //TODO: check constraints
        return userDAO.saveUser(user);
    }

    public User authenticateUser(String username, String password) throws NoSuchUserException, BadPasswordException, SQLException {
           User user = userDAO.getUserByUsername(username);
           if(user == null) {
               throw new NoSuchUserException("User not found");
           } else if(user.getPassword().equals(password)) {
               return user;
           } else {
               throw new BadPasswordException("Password mismatch!");
           }
    }

}