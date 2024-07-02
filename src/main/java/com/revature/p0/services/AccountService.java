package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
}