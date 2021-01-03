package com.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge {
    public static void main(String[] args) {
        String challenge1 = "I want a Bike.";
        System.out.println(challenge1.matches("I want a bike."));

        String challenge2 = "I want a ball.";
        String regex2 = "I want a \\w+.";
        System.out.println(challenge1.matches(regex2));
        System.out.println(challenge2.matches(regex2));
        regex2 = "I want a (bike|ball).";
        System.out.println(challenge1.matches(regex2));
        System.out.println(challenge2.matches(regex2));

        String challenge3 = "Replace all space with underscores.";
        System.out.println(challenge3.replaceAll(" ", "_"));
        System.out.println(challenge3.replaceAll("\\s", "_"));
        System.out.println(challenge3.replaceAll("[ ]", "_"));

        String challenge4 = "aaabccccccccdddefffg";
        System.out.println(challenge4.matches("a+b+c+d+e+f+g+"));
        System.out.println(challenge4.matches("[a-g]+"));
        System.out.println(challenge4.matches("^a{3}bc{8}d{3}ef{3}g"));
        System.out.println(challenge4.replaceAll("^a{3}bc{8}d{3}ef{3}g", "*"));

//        String challenge5="^[a-zA-Z]+[.]{1}[\\d]+$";
        String challenge5 = "^[a-zA-Z]+\\.\\d+$";
        System.out.println("abcdef.1234678".matches(challenge5));
        System.out.println("abcd12.12346a".matches(challenge5));

        String challenge8 = "(\\d+)";
        // for number after alphabets .
        challenge8 = "[A-za-z]+\\.(\\d+)";
        Pattern pattern = Pattern.compile(challenge8);
        Matcher matcher = pattern.matcher("abcd.12346a.12bg.67");
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }

        String challenge9 = "[A-za-z]+\\.(\\d+)\\s";
        pattern = Pattern.compile(challenge9);
        matcher = pattern.matcher("abcd.12346\ta.12\tbg.67\n");
        while (matcher.find()) {
            System.out.println(matcher.group(1) + " start " + matcher.start(1) + " end " + (matcher.end(1) -1));
        }

        String challenge10= "\\{(.*?)\\}";
        pattern = Pattern.compile(challenge10);
        matcher = pattern.matcher("{0, 2}{2, 4}{4, 6}{6, 8}{x, y}{8, 10}");
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }

        String challenge10a= "\\{(\\d+, \\d+)\\}";
        pattern = Pattern.compile(challenge10a);
        matcher = pattern.matcher("{0, 2}{2, 4}{4, 6}{6, 8}{x, y}{8, 10}");
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }

        // US ZIP Code can be 5 digits or 5digits followed by - and 4digits
        System.out.println("11111".matches("^\\d{5}$"));
        System.out.println("11111-1111".matches("^\\d{5}-\\d{4}$"));
        // common solution for US Zip Code pattern
        System.out.println("11111".matches("^\\d{5}(-\\d{4})?$"));
        System.out.println("11111-1111".matches("^\\d{5}(-\\d{4})?$"));
    }
}
