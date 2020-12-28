package com.examples.Banking;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345-678", 1000.00);

        // Create and run threads
//        Thread thread1=new Thread(){
//            @Override
//            public void run() {
//                account.deposit(300);
//                account.withdraw(50);
//            }
//        };
//
//        Thread thread2=new Thread(){
//            @Override
//            public void run() {
//                account.deposit(203.75);
//                account.withdraw(100);
//            }
//        };



        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.printAccountNumber();
                account.deposit(300);
                System.out.println("After depositing: "+ account.getBalance());
                account.withdraw(50);
                System.out.println("After withdrawing: "+ account.getBalance());
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.printAccountNumber();
                account.deposit(203.75);
                System.out.println("After depositing: "+ account.getBalance());
                account.withdraw(100);
                System.out.println("After withdrawing: "+ account.getBalance());
            }
        });

        thread1.start();
        thread2.start();
    }
}
