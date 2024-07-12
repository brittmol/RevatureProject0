package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.models.Account;
import com.revature.p0.exceptions.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public ArrayList<Account> getAllAccountsByUserId(Integer userId) throws SQLException {
        return accountDAO.getAllAccountsByUserId(userId);
    }

    public Account createNewAccount(Account account) throws SQLException {
        return accountDAO.saveAccount(account);
    }

    public Account deleteAccount(Integer accountId) throws CannotDeleteAccountException, SQLException {
        Account foundAccount = accountDAO.getAccountById(accountId);
        if(foundAccount != null) {
            if(foundAccount.getBalance() == 0) {
                return accountDAO.deleteAccount(foundAccount);
            } else {
                throw new CannotDeleteAccountException("Cannot delete account while there is a balance");
            }
        }
        return null;
        // throw account not found exception
    }



}