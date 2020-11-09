import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FunctionClass {

    FunctionClass() {
        this(1, 2);
        System.out.println("********\nFunction Class referenced through Object\n********");
    }

    FunctionClass(int a, int b) {
        System.out.println("Constructor called from default constructor using this keyword at start of constructor");
        System.out.println("a: " + a + " b: " + b);
    }


    public int square(int n) {
        return n * n;
    }

    public static boolean isPrime(int n) {
        if (n == 1) return false;
        for (int i = 2; i <= n / 2; i++)
            if (n % i == 0) return false;
        return true;
    }

    public int reverseDigits(int n) {
        if (n < 10) {
            return n;
        }
        int r = 0;
        while (n != 0) {
            r = r * 10 + n % 10;
            n = n / 10;
        }
        return r;
    }

    public boolean isPalindrome(int n) {
        if (n < 10) {
            return true;
        }
        return n == reverseDigits(n);
    }

    public int gcd(int a, int b) {
        if (a < 1 || b < 1) return -1;
        for (int i = Math.min(a, b); i > 1; i--) {
            if (a % i == 0 && b % i == 0) return i;
        }
        return 1;
    }


    protected void printSquareStar(int number) {
        if (number < 5) System.out.print("Invalid Value");
        else {

            // Loop denoting rows
            for (int i = 0; i < number; i++) {

                // Loop denoting columns
                for (int j = 0; j < number; j++) {

                    // Checking boundary conditions
                    // and main diagonal and
                    // secondary diagonal conditions
                    if (i == 0 || j == 0 || i == j
                            || i == number - 1 || j == number - 1
                            || i + j == number - 1)
                        System.out.print("*");
                    else
                        System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    public void getCurrentDateTime() {
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Default Format : " + now);
        System.out.println("Format yyyy/MM/dd HH:mm:ss : " + dtf1.format(now));
        System.out.println("Format yyyy/MM/dd : " + dtf2.format(now));
        System.out.println("Format HH:mm:ss : " + dtf3.format(now));
        System.out.println("Format dd/MM/yyyy : " + dtf4.format(now));
    }


}
