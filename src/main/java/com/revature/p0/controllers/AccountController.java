package com.revature.p0.controllers;

import com.revature.p0.exceptions.CannotDeleteAccountException;
import com.revature.p0.models.*;
import com.revature.p0.services.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountController {
    Javalin api;
    AccountService accountService;
    UserService userService;

    public AccountController(AccountService accountService, UserService userService, Javalin api) {
        this.accountService = accountService;
        this.userService = userService;
        this.api = api;

        api.get("/accounts/{username}", this::getUserAccounts);
        api.post("/accounts", this::createAccount);
        api.delete("/accounts/{accountId}", this::deleteAccount);
    }

    public void getUserAccounts(Context ctx) throws SQLException {
        String username = ctx.pathParam("username");
        User user = userService.getUserByUsername(username);
        ArrayList<Account> accountList = accountService.getAllAccountsByUserId(user.getId());
        ctx.json(accountList).status(200);
    }

    public void createAccount(Context ctx) throws SQLException {
        Account accountFromBody = ctx.bodyAsClass(Account.class);
        Account newAccount = accountService.createNewAccount(accountFromBody);
        ctx.json(newAccount).status(200);
    }

    public void deleteAccount(Context ctx) throws SQLException, CannotDeleteAccountException {
        Integer accountId = Integer.valueOf(ctx.pathParam("accountId"));
        Account account = accountService.deleteAccount(accountId);
        ctx.json(account).status(200);
    }

}