package com.revature.p0.services;

import com.revature.p0.daos.*;
import com.revature.p0.models.*;
import com.revature.p0.exceptions.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountService {
    AccountDAO accountDAO;
    TransactionDAO transactionDAO;

    public AccountService(AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }

    public ArrayList<Account> getAllAccountsByUserId(Integer userId) throws SQLException {
        return accountDAO.getAllAccountsByUserId(userId);
    }

    public Account createNewAccount(Account account) throws SQLException {
        return accountDAO.saveAccount(account);
    }

    public Account deleteAccount(Account account) throws CannotDeleteAccountException, SQLException, NoSuchUserException {
        try{
            Account findAccount = accountDAO.getAccountById(account.getId());
        } catch (SQLException e) {
            throw new NoSuchUserException("Bank Account not found");
        }

        if(account.getBalance() == 0) {
            return accountDAO.deleteAccount(account);
        } else {
            throw new CannotDeleteAccountException("Cannot delete account while there is a balance");
        }
    }

    public ArrayList<Transaction> getAllTransactionsByAccountId(Integer accountId) throws SQLException {
        return transactionDAO.getAllTransactionsByAccountId(accountId);
    }

    public Transaction createNewTransaction(Transaction transaction) throws SQLException, CannotDeleteAccountException {
        Account account = accountDAO.getAccountById(transaction.getAccountId());

        if(transaction.getTransactionType().equals("withdrawal") && transaction.getAmount() > account.getBalance()) {
            throw new CannotDeleteAccountException("Cannot Withdrawal, not enough funds");
        } else if(transaction.getTransactionType().equals("withdrawal")) {
            account.setBalance(account.getBalance() - transaction.getAmount());
        }
        if(transaction.getTransactionType().equals("deposit")) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
        accountDAO.saveAccount(account);
        return transactionDAO.saveTransaction(transaction);
    }

    // TODO: transwer btwn bank accounts

    /*
    * TODO: withdrawl & deposit
    *  neeed to create a new transaction & update account
    *  need to transwer btwn bacnk accounts
    * */

}