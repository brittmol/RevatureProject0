package com.revature.p0.daos;

import com.revature.p0.models.*;
import com.revature.p0.utils.ConnectionUtil;

import java.io.*;
import java.sql.*;
import java.util.*;

public class TransactionDAO {
    Connection conn;

    public TransactionDAO(Connection conn) throws SQLException, IOException {
        this.conn = ConnectionUtil.getConnection();
    }

    // READ: get ALL transactions (by user id)
    public ArrayList<Transaction> getAllTransactionsByAccountId(Integer accountId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE account_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, accountId);

        ResultSet results = ps.executeQuery();
        ArrayList<Transaction> listOfTransactions = new ArrayList<>();

        // TODO: should we be doing while loop or if?
        while(results.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(results.getInt("id"));
            transaction.setTransactionType(results.getString("transaction_type"));
            transaction.setAmount(results.getDouble("amount"));
            transaction.setAccountId(results.getInt("account_id"));
            listOfTransactions.add(transaction);
        }
        return listOfTransactions;

    }

    // READ: get transaction (by transaction id)
    public Transaction getTransactionById(Integer id) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet results = ps.executeQuery();
        Transaction transaction = new Transaction();
        if(results.next()) {
            transaction.setId(results.getInt("id"));
            transaction.setTransactionType(results.getString("transaction_type"));
            transaction.setAmount(results.getDouble("amount"));
            transaction.setAccountId(results.getInt("account_id"));
        }
        return transaction;
    }

    // CREATE/UPDATE transaction
    public Transaction saveTransaction(Transaction transaction) throws SQLException {
        if(transaction.getId() == null) {
            // CREATE transaction
            String sql = "INSERT INTO transactions(transaction_type, amount, account_id) VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, transaction.getTransactionType());
            ps.setDouble(2, transaction.getAmount());
            ps.setInt(3, transaction.getAccountId());

            ps.executeUpdate();
            ResultSet results = ps.getGeneratedKeys();
            if(results.next()) {
                transaction.setId(results.getInt(1));
            }

        } else {
            // UPDATE transaction
            String sql = "UPDATE transactions SET transaction_type = ?, balance = ?, account_id = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, transaction.getTransactionType());
            ps.setDouble(2, transaction.getAmount());
            ps.setInt(3, transaction.getAccountId());
            ps.setInt(4, transaction.getId());

            ps.executeUpdate();
        }
        return transaction;
    }

}