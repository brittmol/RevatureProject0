package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.exceptions.*;
import com.revature.p0.models.Account;

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


}