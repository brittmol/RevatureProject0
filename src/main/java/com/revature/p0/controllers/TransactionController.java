package com.revature.p0.controllers;

import com.revature.p0.services.TransactionService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class TransactionController {
    Javalin api;
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService, Javalin api) {
        this.api = api;
        this.transactionService = transactionService;
    }
}