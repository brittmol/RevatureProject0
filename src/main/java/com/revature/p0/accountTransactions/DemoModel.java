package com.revature.p0.accountTransactions;

public class DemoModel {
    private String message;
    private int id;

    public DemoModel(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public DemoModel() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
