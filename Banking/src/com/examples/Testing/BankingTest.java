package com.examples.Testing;

import org.junit.*;

import static junit.framework.TestCase.*;

public class BankingTest {
    private Banking banking;
    private static int count = 0;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("this is executed before class. Count: " + (++count));
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("this is executed after class. Count: " + (++count));
    }

    @Before
    public void beforeTest() {
        banking = new Banking("Tim", "Tall", 1000.0, Banking.CHECKING);
        System.out.println("Running Before Test...");
    }

    @After
    public void afterTest() {
        System.out.println("Running After Test...");
    }

    @Test
    public void deposit() {
        // replaced by setup()
//        Banking banking = new Banking("Tim", "Tall", 1000.0,Banking.CHECKING);
        double balance = banking.deposit(200, true);
        // Multiple assertions are allowed, but preferred is only 1 assertion per testcase
        assertEquals(1200.0, balance);
//        assertEquals(1200,balance,0);
//        assertEquals(1200.0,banking.getBalance());
    }

    @org.junit.Test
    public void withdraw_branch() {
        // replaced by setup()
//        Banking banking = new Banking("Tim", "Tall", 1000.0,Banking.CHECKING);
        double balance = banking.withdraw(200, true);
        assertEquals(800.0, balance);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void withdraw_notBranch() {
        // replaced by setup()
//        Banking banking = new Banking("Tim", "Tall", 1000.0,Banking.CHECKING);
        banking.withdraw(600, false);
        // since the expected is already defined, assertion is not required
    }


    // using junit < 4
//    @org.junit.Test
//    public void withdraw_notBranch() {
//        // replaced by setup()
////        Banking banking = new Banking("Tim", "Tall", 1000.0,Banking.CHECKING);
//        try {
//            banking.withdraw(600, false);
//        }catch (IllegalArgumentException e){
//            // since the issue is caught, no further assertions are required
//            // testcase will pass if lower or higher values than 500 are used to withdraw
//        }
//    }

    @org.junit.Test
    public void getBalance_deposit() {
        // replaced by setup()
//        Banking banking = new Banking("Tim", "Tall", 1000.0,Banking.CHECKING);
        banking.deposit(200, true);
        assertEquals(1200.0, banking.getBalance());
    }

    @org.junit.Test
    public void getBalance_withdraw() {
        // replaced by setup()
//        Banking banking = new Banking("Tim", "Tall", 1000.0,Banking.CHECKING);
        banking.withdraw(200, true);
        assertEquals(800.0, banking.getBalance());
    }

    @Test
    public void isChecking_true() {
        // replaced by setup()
//        Banking banking = new Banking("Tim", "Tall", 1000.0,Banking.CHECKING);
        assertTrue(banking.isChecking());
    }

//    @Test
//    public void dummytest() {
//        assertEquals(20,21);
//    }
}
