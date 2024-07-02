package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;

public class UserService {
    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}