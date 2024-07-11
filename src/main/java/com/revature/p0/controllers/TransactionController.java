package com.revature.p0.controllers;

import com.revature.p0.services.TransactionService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;

public class TransactionController {
    Javalin api;
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService, Javalin api) {
        this.api = api;
        this.transactionService = transactionService;

        api.get("/transactions/{accountType}", this::getAccountTransactions);
        api.post("/transactions", this::createTransaction);
    }

    public void getAccountTransactions(Context ctx) throws SQLException {

    }

    public void createTransaction(Context ctx) throws SQLException {

    }

}