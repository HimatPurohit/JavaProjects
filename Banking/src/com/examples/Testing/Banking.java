package com.examples.Testing;

public class Banking {
    private String firstName;
    private String lastName;
    private double balance;

    public static final int CHECKING = 1;
    public static final int SAVING = 2;

    private int accountType;

    public Banking(String firstName, String lastName, double balance, int accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.accountType = accountType;
    }

    // branch is true if customer is transacting at a branch
    // branch is false if customer is transacting at an ATM
    public double deposit(double amount, boolean branch) {
        balance += amount;
        return balance;
    }

    // branch is true if customer is transacting at a branch
    // branch is false if customer is transacting at an ATM
    public double withdraw(double amount, boolean branch) {
        if (amount>500.0 && !branch){
            throw new IllegalArgumentException();
        }
        balance -= amount;
        return balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean isChecking() {
        return this.accountType == CHECKING;
    }

    public boolean isSaving() {
        return this.accountType == SAVING;
    }

    // Other methods that maybe required
}
