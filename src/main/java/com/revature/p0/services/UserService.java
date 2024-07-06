package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.BadPasswordException;
import com.revature.p0.exceptions.NoSuchUserException;
import com.revature.p0.models.User;

import java.sql.SQLException;

public class UserService {
    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User registerNewUser(User user) throws SQLException {
        //TODO: check if username already exists and other constraints
        return userDAO.saveUser(user);
    }

    public User authenticateUser(String username, String password) throws NoSuchUserException, BadPasswordException {
        User user;
        try{
           user = userDAO.getUserByUsername(username);
        } catch (SQLException e) {
            throw new NoSuchUserException("User not found");
        }

        if(user.getPassword().equals(password)) {
            return user;
        } else {
            throw new BadPasswordException("Password mismatch!");
        }
    }

}