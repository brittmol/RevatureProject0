package com.revature.p0.models;

import java.util.List;

public class Account {
    private Integer id;
    private String accountType;
    private Double balance;
    private Integer userId;
    List<Transaction> transactions;

    // create constructor
    public Account() {

    }

    public Account(String accountType, Double balance, Integer userId) {
        this.accountType = accountType;
        this.balance = balance;
        this.userId = userId;
    }

    // create getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}