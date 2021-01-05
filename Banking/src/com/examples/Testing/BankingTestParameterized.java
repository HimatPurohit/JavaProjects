package com.examples.Testing;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class BankingTestParameterized {
    private Banking banking;

    private double amount;
    private boolean branch;
    private double expected;

    // Constructor is used to set parameterized values to each variable
    public BankingTestParameterized(double amount, boolean branch, double expected) {
        this.amount = amount;
        this.branch = branch;
        this.expected = expected;
    }

    @Before
    public void beforeTest() {
        banking = new Banking("Tim", "Tall", 1000.0, Banking.CHECKING);
        System.out.println("Running Before Test...");
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        return Arrays.asList(new Object[][]{
                {100.00,true,1100.00},
                {200.00,true,1200.00},
                {300.00,true,1300.00},
                {400.14,true,1400.14},
                {500.33,true,1500.33},
                {1000.00,true,2000.00}
        });
    }

    @Test
    public void deposit() {
        // replaced by beforeTest()
//        Banking banking = new Banking("Tim", "Tall", 1000.0,Banking.CHECKING);
        double balance = banking.deposit(amount, branch);
        assertEquals(expected, balance,0.1);
    }
}
