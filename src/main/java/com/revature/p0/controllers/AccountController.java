package com.revature.p0.controllers;

import com.revature.p0.services.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class AccountController {
    Javalin api;
    AccountService accountService;

    public AccountController(AccountService accountService, Javalin api) {
        this.api = api;
        this.accountService = accountService;

        api.get("/accounts/{username}", this::getUserAccounts);
        api.post("/accounts", this::createAccount);
        api.delete("/accounts/{accountType}", this::deleteAccount);
    }

    public void getUserAccounts(Context ctx) {

    }

    public void createAccount(Context ctx) {

    }

    public void deleteAccount(Context ctx) {

    }

}