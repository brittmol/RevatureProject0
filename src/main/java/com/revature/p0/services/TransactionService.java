package com.revature.p0.services;

import com.revature.p0.daos.TransactionDAO;

public class TransactionService {
    TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }
}