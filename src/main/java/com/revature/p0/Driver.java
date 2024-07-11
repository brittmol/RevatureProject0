package com.revature.p0;
import com.revature.p0.controllers.AccountController;
import com.revature.p0.controllers.TransactionController;
import com.revature.p0.controllers.UserController;
import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.TransactionDAO;
import com.revature.p0.daos.UserDAO;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.TransactionService;
import com.revature.p0.services.UserService;
import com.revature.p0.utils.ConnectionUtil;
import io.javalin.Javalin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Javalin api = Javalin.create().start(7777); //
        Connection conn = ConnectionUtil.getConnection();

        UserDAO userDAO = new UserDAO(conn);
        AccountDAO accountDAO = new AccountDAO(conn);
        TransactionDAO transactionDAO = new TransactionDAO(conn);

        UserService userService = new UserService(userDAO);
        AccountService accountService = new AccountService(accountDAO);
        TransactionService transactionService = new TransactionService(transactionDAO, accountDAO);

        UserController userController = new UserController(userService, api);
        AccountController accountController = new AccountController(accountService, userService, api);
        TransactionController transactionController = new TransactionController(transactionService, api);
    }
}