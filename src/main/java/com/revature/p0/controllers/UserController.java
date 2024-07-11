package com.revature.p0.controllers;

import com.revature.p0.exceptions.BadPasswordException;
import com.revature.p0.exceptions.NoSuchUserException;
import com.revature.p0.models.User;
import com.revature.p0.services.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;

public class UserController {
    Javalin api;
    UserService userService;

    public UserController(UserService userService, Javalin api) {
        this.api = api;
        this.userService = userService;

        // *** request handlers ***
        api.get("/users/{username}", this::getUserByUsername);
        api.post("/login", this::login);
        api.post("/register", this::register);
        api.put("/users/{username}", this::updateUser);
//        api.delete("/users/{username}", this::deleteUser);
    }

    public void getUserByUsername(Context ctx) throws SQLException {
        User user = userService.getUserByUsername(ctx.pathParam("username"));
        ctx.json(user).status(200);
    }

    public void login(Context ctx) throws BadPasswordException, NoSuchUserException, SQLException {
        User userFromBody = ctx.bodyAsClass(User.class);
        User resultUser = userService.authenticateUser(userFromBody.getUsername(), userFromBody.getPassword());
        // if resultUser != null --> else send 401?
        ctx.json(resultUser).status(200);

        ctx.header("Auth", resultUser.getUsername()); // why is this necessary? alternate for session persistance
    }

    public void register(Context ctx) throws SQLException {
        User userFromBody = ctx.bodyAsClass(User.class);
        User resultUser = userService.registerNewUser(userFromBody);
        // if resultUser != null --> else send 401?
        ctx.json(resultUser).status(200);
    }

    public void updateUser(Context ctx) throws SQLException {
        User userFromBody = ctx.bodyAsClass(User.class);
        User resultUser = userService.updateUser(userFromBody);
        // if resultUser != null --> else send 401?
        ctx.json(resultUser).status(200);
    }

//    public void deleteUser(Context ctx) throws SQLException {
//        String usernameFromPath = ctx.pathParam("username");
//        //TODO: delete user
//    }



}