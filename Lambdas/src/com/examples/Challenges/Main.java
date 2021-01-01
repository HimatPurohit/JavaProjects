package com.examples.Challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Challenge 1
        System.out.println("----------Challenge 1----------");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                String myString = "Lets split this into an array";
                String[] parts = myString.split(" ");
                for (String string : parts) {
                    System.out.println(string);
                }
            }
        };

        Runnable lambdaRunnable = () -> {
            System.out.println(Thread.currentThread().getName());
            String myString = "Lets split this into an array";
            String[] parts = myString.split(" ");
            for (String string : parts) {
                System.out.println(string);
            }
        };


        // Note: run() is used instead of start() as run will use the main thread
        new Thread(runnable).run();
        new Thread(lambdaRunnable).run();
//        new Thread(runnable).start();
//        new Thread(lambdaRunnable).start();


        // Challenge 2 and Challenge 3
        System.out.println("----------Challenge 2 & 3----------");
        System.out.println(everySecondChar("Hello World!"));
        System.out.println(everySecondChar("1234567890"));

        Function<String, String> everySecondCharFunction = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); ++i) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
        System.out.println(everySecondCharFunction.apply("Hello World!"));
        System.out.println(everySecondCharFunction.apply("1234567890"));


        // Challenge 4
        System.out.println("----------Challenge 4----------");
//        Scanner sc=new Scanner(System.in);
//        System.out.println("Enter an input String");
//        String input=sc.next();
//        System.out.println(everySecondCharacter(everySecondCharFunction,input));
        System.out.println(everySecondCharacter(everySecondCharFunction, "1234567890"));

        // Challenge 5
        System.out.println("----------Challenge 5----------");
        Supplier<String> iLoveJava = () -> "I Love Java";
        System.out.println(iLoveJava.get());

        // Challenge 6
        System.out.println("----------Challenge 6----------");
        List<String> names = Arrays.asList("Amelia",
                "Olivia",
                "emily",
                "isla",
                "Ava",
                "oliver",
                "jack",
                "Charlie",
                "harry",
                "Jacob");

        List<String> firstCharUpperCase = new ArrayList<>();
        names.forEach(name -> firstCharUpperCase.add(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()));
//        firstCharUpperCase.sort((s1,s2)->s1.compareTo(s2));
        firstCharUpperCase.sort(String::compareTo);
        firstCharUpperCase.forEach(System.out::println);


        // Challenge 7
        System.out.println("----------Challenge 7----------");
        names.stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase())
//                .sorted()
                .sorted(String::compareTo)
                .forEach(System.out::println);

        // Challenge 8
        System.out.println("----------Challenge 8----------");
        long countNamesStartWithA = names.stream()
                .filter(name -> name.startsWith("A"))
                .peek(System.out::println)
                .count();
        System.out.println(countNamesStartWithA);


        // Challenge 9
        System.out.println("----------Challenge 9----------");
        names.stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase())
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(Collectors.toList());

    }

    // Challenge 2
    public static String everySecondChar(String source) {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); ++i) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

    // Challenge 4
    public static String everySecondCharacter(Function<String, String> everySecondCharFunction, String source) {
        return everySecondCharFunction.apply(source);
    }
}