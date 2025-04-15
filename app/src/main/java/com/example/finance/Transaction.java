package com.example.finance;

import java.util.Date;

public class Transaction {
    private long id;
    private String description;
    private double amount;
    private Date date;
    private String category;
    private String accountName;
    private TransactionType type;

    public enum TransactionType {
        EXPENSE,
        INCOME,
        TRANSFER
    }

    public Transaction(long id, String description, double amount, Date date,
                       String category, String accountName, TransactionType type) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.accountName = accountName;
        this.type = type;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getAccountName() {
        return accountName;
    }

    public TransactionType getType() {
        return type;
    }
}
