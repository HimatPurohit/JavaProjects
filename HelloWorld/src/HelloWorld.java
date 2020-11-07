public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        System.out.println("Hello "+args[0]);

        int number = 10;
        int a = ++number;
        int b = --number;
//        System.out.println("number:"+number+" number++:"+(number++)+" number--:"+(number--));
        System.out.println(a + " " + b);

        a = 5;
        b = 3;


        //swapping a and b using arithmetic operators
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a: " + a + " b: " + b);

        //swapping a and b using arithmetic operators
        a = a * b;
        b = a / b;
        a = a / b;
        System.out.println("a: " + a + " b: " + b);

        //swapping a and b using Bitwise XOR operators
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a: " + a + " b: " + b);


        //Primitive Data Types
        int maxint = Integer.MAX_VALUE;
        int minint = Integer.MIN_VALUE;
        System.out.println("Maxint: " + maxint + "\nMinint: " + minint);

        int maxbyte = Byte.MAX_VALUE;
        int minbyte = Byte.MIN_VALUE;
        System.out.println("Maxbyte: " + maxbyte + "\nMinbyte: " + minbyte);
        System.out.println("Maxshort: " + Short.MAX_VALUE + "\nMinshort: " + Short.MIN_VALUE);
        System.out.println("Maxlong: " + Long.MAX_VALUE + "\nMinlong: " + Long.MIN_VALUE);
        System.out.println('\u0041' + "to" + '\u005A');
        System.out.println(45 + "number");
        a = 60;
//        b = 13;
//        if(a & b) System.out.printf(a&b);
        FunctionClass functionClass=new FunctionClass();
        System.out.println("square of " + a + " = " + functionClass.square(a));
//        String s="defg";
//        switch (s){
//            case "abcd":
//                System.out.println(s);
//                break;
//            case "defg":
//                System.out.println(s);
//                break;
//            default:
//                System.out.println(s);
//        }
        int sum = 0;
        int count = 0;
        for (int i = 1; i <= 1000; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println(i);
                sum += i;
                count++;
            }
            if (count == 5) {
                System.out.println("sum:" + sum);
                break;
            }
        }

        System.out.println("\nPrimes Numbers between 1 and 100:");
        count = 0;
        for (int i = 0; i <= 100; i++) {
            if (FunctionClass.isPrime(i)) {
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.println("\n" + count + " Prime numbers found");

        int rnum = 12321;
        System.out.println("Reverse of " + rnum + " is " + functionClass.reverseDigits(rnum));
        System.out.println(rnum + " is palindrome : " + functionClass.isPalindrome(rnum));

        a = 12;
        b = 30;
        System.out.println("GCD of a: " + a + " and b : " + b + " is " + functionClass.gcd(a, b));
        a = 25;
        b = 15;
        System.out.println("GCD of a: " + a + " and b : " + b + " is " + functionClass.gcd(a, b));
        a = 9;
        b = 18;
        System.out.println("GCD of a: " + a + " and b : " + b + " is " + functionClass.gcd(a, b));
        a = 81;
        b = 153;
        System.out.println("GCD of a: " + a + " and b : " + b + " is " + functionClass.gcd(a, b));

        functionClass.printSquareStar(10);

//        Scanner scanner=new Scanner(System.in);
//        System.out.println("Enter 3 integer values");
//        if (scanner.hasNextInt()){
//            int p=scanner.nextInt();
//            int q=scanner.nextInt();
//            int r=scanner.nextInt();
//            System.out.println(p+q+r);
//        }
//        scanner.close();
    }


}

