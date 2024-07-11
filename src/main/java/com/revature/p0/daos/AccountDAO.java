package com.revature.p0.daos;

import com.revature.p0.models.*;
import com.revature.p0.utils.ConnectionUtil;

import java.io.*;
import java.sql.*;
import java.util.*;

public class AccountDAO {
    Connection conn;

    public AccountDAO(Connection conn) throws SQLException, IOException {
        this.conn = ConnectionUtil.getConnection();
    }

    // READ: get ALL accounts (by user id)
    public ArrayList<Account> getAllAccountsByUserId(Integer userId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet results = ps.executeQuery();
        ArrayList<Account> listOfAccounts = new ArrayList<>();

        // TODO: should we be doing while loop or if?
        while(results.next()) {
            Account account = new Account();
            account.setId(results.getInt("id"));
            account.setAccountType(results.getString("account_type"));
            account.setBalance(results.getDouble("balance"));
            account.setUserId(results.getInt("user_id"));
            listOfAccounts.add(account);
        }
        return listOfAccounts;

    }

    // READ: get account (by account id)
    public Account getAccountById(Integer id) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet results = ps.executeQuery();
        Account account = new Account();
        if(results.next()) {
            account.setId(results.getInt("id"));
            account.setAccountType(results.getString("account_type"));
            account.setBalance(results.getDouble("balance"));
            account.setUserId(results.getInt("user_id"));
        }
        return account;
    }

    // CREATE/UPDATE account
    public Account saveAccount(Account account) throws SQLException {
        if(account.getId() == null) {
            // CREATE account
            String sql = "INSERT INTO accounts(account_type, balance, user_id) VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getAccountType());
            ps.setDouble(2, account.getBalance());
            ps.setInt(3, account.getUserId());

            ps.executeUpdate();
            ResultSet results = ps.getGeneratedKeys();
            if(results.next()) {
                account.setId(results.getInt(1));
            }

        } else {
            // UPDATE account
            String sql = "UPDATE accounts SET account_type = ?, balance = ?, user_id = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getAccountType());
            ps.setDouble(2, account.getBalance());
            ps.setInt(3, account.getUserId());
            ps.setInt(4, account.getId());

            ps.executeUpdate();
        }
        return account;
    }


    // DELETE account TODO: check if correct
    public Account deleteAccount(Account account) throws SQLException {
        String sql = "DELETE FROM accounts WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, account.getId());
        ps.executeUpdate();
        return account;
    }

}