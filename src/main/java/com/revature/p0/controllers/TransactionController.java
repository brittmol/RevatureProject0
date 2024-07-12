package com.revature.p0.controllers;

import com.revature.p0.models.*;
import com.revature.p0.services.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class TransactionController {
    Javalin api;
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService, Javalin api) {
        this.api = api;
        this.transactionService = transactionService;

        api.get("/transactions/{accountId}", this::getAccountTransactions);
        api.post("/transactions", this::createTransaction);
    }

    public void getAccountTransactions(Context ctx) throws SQLException {
        Integer accountId = Integer.valueOf(ctx.pathParam("accountId"));
        ArrayList<Transaction> transactionList = transactionService.getAllTransactionsByAccountId(accountId);
        ctx.json(transactionList).status(200);
    }

    public void createTransaction(Context ctx) throws SQLException {
        Map<String, Object> transactionFromBody = ctx.bodyAsClass(Map.class);
        String transactionType = (String) transactionFromBody.get("transactionType");
        Double amount = (Double) transactionFromBody.get("amount");
        Integer accountId = (Integer) transactionFromBody.get("accountId");

//        String transactionType = ctx.formParam("transactionType");
//        Double amount = Double.valueOf(ctx.formParam("amount"));
//        Integer accountId = Integer.valueOf(ctx.formParam("accountId"));

        switch (transactionType.toLowerCase()) {
            case "deposit":
                transactionService.deposit(accountId, amount);
                ctx.status(201).result("Deposit successful");
                break;
            case "withdrawal":
                transactionService.withdrawal(accountId, amount);
                ctx.status(201).result("Withdrawal successful");
                break;
            case "transfer":
//                Integer toAccountId = Integer.valueOf(ctx.formParam("toAccountId"));
                Integer toAccountId = (Integer) transactionFromBody.get("toAccountId");
                transactionService.transfer(accountId, toAccountId, amount);
                ctx.status(201).result("Transfer successful");
                break;
            default:
                ctx.status(400).result("Invalid transaction type");
                return;
        }

    }

}