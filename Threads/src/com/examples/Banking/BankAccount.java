package com.examples.Banking;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private String accountNumber;
    private ReentrantLock lock = new ReentrantLock();

    public BankAccount(String accountNumber, double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account Number: " + accountNumber);
    }

    // Using synchronized keyword
//    public synchronized void deposit(double amount){
//        if (amount>0){
//            balance+=amount;
//        }
//    }
//    public synchronized void withdraw(double amount){
//        if (balance-amount >=0){
//            balance-=amount;
//        }
//    }

    // Using synchronized method
//    public void deposit(double amount){
//        synchronized (this){
//            if (amount>0){
//                balance+=amount;
//            }
//        }
//    }
//
//    public void withdraw(double amount){
//        synchronized (this) {
//            if (balance-amount >=0){
//                balance-=amount;
//            }
//        }
//    }


    // Using ReentrantLock and trylock with timeout
    public void deposit(double amount) {
        boolean status=false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    if (amount > 0) {
                        balance += amount;
                        status=true;
                    }
                }finally {
                    lock.unlock();
                }
            }else {
                System.out.println("Could not get the Lock");
            }
        } catch (InterruptedException e) {

        }
        System.out.println("Transaction status: "+status);
    }

    public void withdraw(double amount) {
        boolean status=false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    if (balance-amount >= 0) {
                        balance -= amount;
                        status=true;
                    }
                }finally {
                    lock.unlock();
                }
            }else {
                System.out.println("Could not get the Lock");
            }
        } catch (InterruptedException e) {
            System.out.println("Could not get the Lock");
        }
        System.out.println("Transaction status: "+status);
    }
}
