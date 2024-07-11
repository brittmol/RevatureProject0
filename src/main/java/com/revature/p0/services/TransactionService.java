package com.revature.p0.services;

import com.revature.p0.daos.*;
import com.revature.p0.models.*;
import com.revature.p0.exceptions.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionService {
    TransactionDAO transactionDAO;
    AccountDAO accountDAO;

    public TransactionService(TransactionDAO transactionDAO, AccountDAO accountDAO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }

    public ArrayList<Transaction> getAllTransactionsByAccountId(Integer accountId) throws SQLException {
        return transactionDAO.getAllTransactionsByAccountId(accountId);
    }

    public void deposit(Integer accountId, Double amount) throws SQLException {
        Account account = accountDAO.getAccountById(accountId);
        if(account != null) {
            account.setBalance(account.getBalance() + amount);
            accountDAO.saveAccount(account);
            Transaction transaction = new Transaction("deposit", amount, accountId);
            transactionDAO.saveTransaction(transaction);
        } // else throw exception "Account not found"
    }

    public void withdrawal(Integer accountId, Double amount) throws SQLException {
        Account account = accountDAO.getAccountById(accountId);
        if(account != null) {
            if(account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountDAO.saveAccount(account);
                Transaction transaction = new Transaction("withdrawal", amount, accountId);
                transactionDAO.saveTransaction(transaction);
            } // else throw exception "Insufficient funds"
        } // else throw exception "Account not found"
    }

    public void transfer(Integer fromAccountId, Integer toAccountId, Double amount) throws SQLException {
        Account fromAccount = accountDAO.getAccountById(fromAccountId);
        Account toAccount = accountDAO.getAccountById(toAccountId);

        if(fromAccount != null && toAccount != null) {
            if(fromAccount.getBalance() >= amount) {
                withdrawal(fromAccountId, amount);
                deposit(toAccountId, amount);
            } // else throw exception "Insufficient funds"
        } // else throw exception "Account not found"
    }

}