import java.lang.reflect.Array;
import java.util.*;

public class HelloWorld {
    enum BodyTypes {
        STAR, PLANET, DWARF_PLANET,MOON,COMET,ASTEROID
    }

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
        FunctionClass functionClass = new FunctionClass();

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
        functionClass.getCurrentDateTime();

//        Scanner scanner=new Scanner(System.in);
//        System.out.println("Enter 3 integer values");
//        if (scanner.hasNextInt()){
//            int p=scanner.nextInt();
//            int q=scanner.nextInt();
//            int r=scanner.nextInt();
//            System.out.println(p+q+r);
//        }
//        scanner.close();

        int[] arrInt = new int[10];
        System.out.println("Before initializing arrInt:");
        for (int i = 0; i < arrInt.length; i++) {
            System.out.println("arrInt[" + i + "] is " + arrInt[i] + " ");
        }
        System.out.println(Arrays.toString(arrInt));


        for (int i = 0; i < arrInt.length; i++) {
            arrInt[i] = i * 2;
        }

//        updateArr(arrInt);

        System.out.println("After initializing arrInt to i*2:");
        for (int i = 0; i < arrInt.length; i++) {
            System.out.println("arrInt[" + i + "] is " + arrInt[i] + " ");
        }
        System.out.println(Arrays.toString(arrInt));

        for (int i = 1; i <= arrInt.length; i++) {
            arrInt[arrInt.length - i] = i * 4;
        }

        System.out.println("Before sorting arrInt:");
        for (int i = 0; i < arrInt.length; i++) {
            System.out.println("arrInt[" + i + "] is " + arrInt[i] + " ");
        }
        System.out.println(Arrays.toString(arrInt));

        FunctionClass.arrSortAsc(arrInt);
        System.out.println("After sorting arrInt in Ascending:");
        for (int i = 0; i < arrInt.length; i++) {
            System.out.println("arrInt[" + i + "] is " + arrInt[i] + " ");
        }
        System.out.println(Arrays.toString(arrInt));

        FunctionClass.arrSortDesc(arrInt);
        System.out.println("After sorting arrInt in Descending:");
        for (int i = 0; i < arrInt.length; i++) {
            System.out.println("arrInt[" + i + "] is " + arrInt[i] + " ");
        }
        System.out.println(Arrays.toString(arrInt));

        FunctionClass.reverseArr(arrInt);
        System.out.println("After Reversing arrInt:");
        System.out.println(Arrays.toString(arrInt));

        System.out.println(arrInt.length);


        List<Integer> al = new ArrayList<Integer>();
        al.add(10);
        al.add(20);
        al.add(30);
        al.add(40);

        Integer[] arr = new Integer[10];
        arr = al.toArray(arr);

//        for (Integer x : arr)
//            System.out.print(x + " ");
        System.out.println(Arrays.toString(arr));
        System.out.println(arr.length);

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
//        list.add("abc");
        list.add(4);
        list.add(5);
        for (Integer i : list) {
            System.out.println(i);
        }
        ;


//        pyramid(5);
//        pyramid(10);
//        pyramid(20);

        FunctionClass.arrSortAsc(arrInt);
        System.out.println(Arrays.toString(arrInt));
        System.out.println(functionClass.searchValueInArray(arrInt, 4));
        System.out.println(functionClass.searchValueInArray(arrInt, 8));
        System.out.println(functionClass.searchValueInArray(arrInt, 12));
        System.out.println(functionClass.searchValueInArray(arrInt, 16));
        System.out.println(functionClass.searchValueInArray(arrInt, 20));
        System.out.println(functionClass.searchValueInArray(arrInt, 24));
        System.out.println(functionClass.searchValueInArray(arrInt, 50));


        Set<Integer> square = new HashSet<>();
        Set<Integer> cube = new HashSet<>();

        for (int i = 1; i <= 100; i++) {
            square.add(i * i);
            cube.add(i * i * i);
        }
        System.out.println(square.size() + " squares and " + cube.size() + " cubes.");
        Set<Integer> union = new HashSet<>(square);
        union.addAll(cube);
        Set<Integer> intersection = new HashSet<>(square);
        intersection.retainAll(cube);
        System.out.println(union.size() + " unions and " + intersection.size() + " intersections.");
        System.out.println(Arrays.toString(intersection.toArray()));
        System.out.println(intersection.toString());

        for (int i : intersection) {
            System.out.println(i);
        }

        System.out.println("Square contains all intersection: " + square.containsAll(intersection));
        union.removeAll(intersection);
        System.out.println(union.size() + " unions afters removing intersections");

        System.out.println(BodyTypes.ASTEROID);

    }


}

