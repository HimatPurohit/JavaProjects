import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        int x=98;
//        int y=0;
//        // The below will handle ArithmeticException divide by Zero
//        System.out.println(divideLBYL(x,y));
//        System.out.println(divideEAFP(x,y));
//        // The below will generate ArithmeticException divide by Zero
//        System.out.println(divide(x,y));

        int x = getInt();
        int y = getIntLBYL();
        int z = getIntEAFP();
        int n = getIntOnly();
        System.out.println("x is " + x + "\ny is " + y + "\nz is " + z + "\nn is " + n);
    }

    private static int divideLBYL(int x, int y) {
        if (y != 0) {
            return x / y;
        } else {
            return 0;
        }
    }

    private static int divideEAFP(int x, int y) {
        try {
            return x / y;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    private static int divide(int x, int y) {
        return x / y;
    }

    private static int getInt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Number");
        return scanner.nextInt();
    }

    private static int getIntOnly() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Number");
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Enter a number using digits 0 to 9:");
            }
        }
    }

    private static int getIntLBYL() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;
        System.out.println("Please enter an integer:");
        String input = scanner.next();
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            return Integer.parseInt(input);
        }
        return 0;
    }

    private static int getIntEAFP() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an integer:");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return 0;
        }
    }
}
