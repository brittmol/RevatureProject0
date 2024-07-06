package com.revature.p0.models;

public class Transaction {
    private Integer id;
    private String transactionType;
    private Double amount;
    private Integer accountId;

    // create constructor
    public Transaction() {

    }

    public Transaction(String transactionType, Double amount, Integer accountId) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountId = accountId;
    }

    // create getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}